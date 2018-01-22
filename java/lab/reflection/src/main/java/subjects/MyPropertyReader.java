package subjects;

import model.Person;

public final class MyPropertyReader implements PropertyReader {
    public Object executeGetter(Object object) {
        return ((Person)object).getName();
    }
}
