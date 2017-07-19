package hello;

/**
 * Created by tzuyichao on 17/07/2017.
 */
public class AdvOptionSchema {
    private String name;
    private String type;
    private boolean isRequired;

    public AdvOptionSchema() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();

        ret.append(String.format("[name: %s, type: %s, isRequired: %b]\n", name, type, isRequired));

        return ret.toString();
    }
}
