package subjects;

import model.Person;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MyMethodHandlePropertyReader implements PropertyReader {
    private final MethodHandle getterMethodHandle;

    public MyMethodHandlePropertyReader() {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            MethodHandle method = lookup.findVirtual(Person.class, "getName", MethodType.methodType(String.class));
            method.asType(method.type().changeParameterType(0, Object.class));
            getterMethodHandle = method.asType(method.type().changeReturnType(Object.class));
        } catch(Exception e) {
            throw new IllegalStateException("method handle create failed");
        }
    }

    public Object executeGetter(Object bean) {
        try {
            return getterMethodHandle.invokeExact((Person)bean);
        } catch(Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
