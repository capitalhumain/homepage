package subjects;

import model.Person;

public final class MyAccessor implements  PropertyAccessor {
    public Object executeGetter(Object object) {
        return ((Person)object).getName();
    }
}
