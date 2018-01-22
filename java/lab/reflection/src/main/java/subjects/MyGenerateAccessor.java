package subjects;

import model.Person;

import javax.tools.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MyGenerateAccessor implements PropertyAccessor {
    private final PropertyAccessor generatedPropertyAccessor;

    private final JavaCompiler javaCompiler;
    private final MyClassLoader classLoader;
    private final DiagnosticCollector<JavaFileObject> diagnosticCollector;

    public MyGenerateAccessor() {
        javaCompiler = ToolProvider.getSystemJavaCompiler();
        if(null == javaCompiler) {
            throw new IllegalArgumentException("cannot get System JavaCompiler instance from ToolProvider");
        }
        classLoader = new MyClassLoader(MyGenerateAccessor.class.getClassLoader());
        diagnosticCollector = new DiagnosticCollector<>();

        generatedPropertyAccessor = generate();

    }

    public PropertyAccessor generate() {
        String getterName = "getName";
        String packageName = String.format("%s.generated.%s", MyGenerateAccessor.class.getPackage().getName(), Person.class.getPackage().getName());
        String className = "Person$getName";
        String fullClassName = String.format("%s.%s", packageName, className);

        final String source = "package " + packageName + ";\n" +
                "import model.Person;\n" +
                "public class " + className + " implements " + PropertyAccessor.class.getName() + " {\n" +
                "    public Object executeGetter(Object bean) {\n" +
                "        return ((Person) bean)." + getterName + "();\n" +
                "    }\n" +
                "}";

        // compile
        JavaFileManager standardFileManager = javaCompiler.getStandardFileManager(diagnosticCollector, null, null);
        SimpleJavaFileObject javaFileObject = new MyJavaFileObject(fullClassName, source);

        JavaFileManager fileManager = new MyFileManager(standardFileManager, classLoader);

        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, fileManager, diagnosticCollector, null, null, Collections.singletonList(javaFileObject));
        boolean success = task.call();
        if(!success) {
            final Pattern linePattern = Pattern.compile("\n");
            String compilationMessages = diagnosticCollector.getDiagnostics().stream()
                    .map(d -> d.getKind() + ":[" + d.getLineNumber() + "," + d.getColumnNumber() +"] " + d.getMessage(null)
                            + "\n        " + (d.getLineNumber() <= 0 ? "" : linePattern.splitAsStream(source).skip(d.getLineNumber() - 1).findFirst().orElse("")))
                    .collect(Collectors.joining("\n"));
            throw new IllegalStateException("The generated class (" + fullClassName + ") failed to compile.\n"
                    + compilationMessages);
        }

        Class<PropertyAccessor> compiledClass;
        try {
            compiledClass = (Class<PropertyAccessor>) classLoader.loadClass(fullClassName);
        } catch(ClassNotFoundException e) {
            throw new IllegalStateException("The generated class (" + fullClassName
                    + ") compiled, but failed to load.", e);
        }
        try {
            return compiledClass.newInstance();
        } catch(Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public Object executeGetter(Object bean) {
        return generatedPropertyAccessor.executeGetter(bean);
    }

    class MyJavaFileObject extends SimpleJavaFileObject {
        private String source;
        public MyJavaFileObject(String fullClassName, String source) {
            super(URI.create(String.format("string:///%s%s", fullClassName.replace('.', '/'), Kind.SOURCE.extension)), Kind.SOURCE);
            this.source = source;
        }

        @Override
        public String getCharContent(boolean ignoreEncodingErrors) {
            return source;
        }
    }

    class MyGeneratedClassFileObject extends SimpleJavaFileObject {

        private ByteArrayOutputStream classOutputStream;

        public MyGeneratedClassFileObject(String fullClassName) {
            super(URI.create("bytes:///" + fullClassName), Kind.CLASS);
        }

        @Override
        public InputStream openInputStream() {
            return new ByteArrayInputStream(getClassBytes());
        }

        @Override
        public OutputStream openOutputStream() {
            classOutputStream = new ByteArrayOutputStream();
            return classOutputStream;
        }

        public byte[] getClassBytes() {
            return classOutputStream.toByteArray();
        }

    }

    class MyFileManager extends ForwardingJavaFileManager<JavaFileManager> {
        private final MyClassLoader classLoader;

        public MyFileManager(JavaFileManager fileManager, MyClassLoader classLoader) {
            super(fileManager);
            this.classLoader = classLoader;
        }

        @Override
        public JavaFileObject getJavaFileForOutput(Location location, String qualifiedName, JavaFileObject.Kind kind, FileObject sibling) {
            if (kind != JavaFileObject.Kind.CLASS) {
                throw new IllegalArgumentException("Unsupported kind (" + kind + ") for class (" + qualifiedName + ").");
            }
            MyGeneratedClassFileObject fileObject = new MyGeneratedClassFileObject(qualifiedName);
            classLoader.addJavaFileObject(qualifiedName, fileObject);
            return fileObject;
        }

        @Override
        public ClassLoader getClassLoader(Location location) {
            return classLoader;
        }
    }

    class MyClassLoader extends ClassLoader {
        private final Map<String, MyGeneratedClassFileObject> fileObjectMap = new HashMap<>();

        public MyClassLoader(ClassLoader parent) {
            super(parent);
        }

        @Override
        protected Class<?> findClass(String fullClassName) throws ClassNotFoundException {
            MyGeneratedClassFileObject fileObject = fileObjectMap.get(fullClassName);
            if (fileObject != null) {
                byte[] classBytes = fileObject.getClassBytes();
                return defineClass(fullClassName, classBytes, 0, classBytes.length);
            }
            return super.findClass(fullClassName);
        }

        public void addJavaFileObject(String qualifiedName, MyGeneratedClassFileObject fileObject) {
            fileObjectMap.put(qualifiedName, fileObject);
        }
    }
}
