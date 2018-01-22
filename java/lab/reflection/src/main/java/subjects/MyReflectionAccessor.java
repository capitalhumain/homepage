package subjects;

import model.Person;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyReflectionAccessor implements PropertyAccessor {
    private final Method getterMethod;

    public MyReflectionAccessor() {
        String getterName = "getName";

        try {
            getterMethod = Person.class.getMethod(getterName);
        } catch(NoSuchMethodException e) {
            throw new IllegalStateException("getter not found");
        }
        getterMethod.setAccessible(true);
    }

    public Object executeGetter(Object bean) {
        try {
            return getterMethod.invoke(bean);
        } catch(IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("invoke getter method failed");
        }
    }
}
