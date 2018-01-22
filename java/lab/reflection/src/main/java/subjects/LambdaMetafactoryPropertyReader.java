package subjects;

import model.Person;

import java.lang.invoke.*;
import java.lang.reflect.Method;
import java.util.function.Function;

public class LambdaMetafactoryPropertyReader implements PropertyReader {
    private final Function getterFunction;

    public LambdaMetafactoryPropertyReader() {
        String getter = "getName";
        Method getterMethod;
        try {
            getterMethod = Person.class.getMethod(getter);
        } catch(NoSuchMethodException e) {
            throw new IllegalArgumentException(String.format("%s class does not have %", Person.class.getSimpleName(), getter));
        }
        Class<?> returnType = getterMethod.getReturnType();

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        CallSite callSite;
        try {
            callSite = LambdaMetafactory.metafactory(lookup,
                    "apply",
                    MethodType.methodType(Function.class),
                    MethodType.methodType(Object.class, Object.class),
                    lookup.findVirtual(Person.class, getter, MethodType.methodType(returnType)),
                    MethodType.methodType(returnType, Person.class)
                    );
        } catch(LambdaConversionException | NoSuchMethodException | IllegalAccessException e) {
            throw new IllegalArgumentException(String.format("Lambda creation failed for method: %s", getter), e);
        }
        try {
            getterFunction = (Function) callSite.getTarget().invokeExact();
        } catch(Throwable e) {
            throw new IllegalArgumentException(String.format("Lambda creation failed for method: %s", getter), e);
        }
    }

    public Object executeGetter(Object bean) {
        return getterFunction.apply(bean);
    }
}
