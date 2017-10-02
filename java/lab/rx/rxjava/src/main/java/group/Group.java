package group;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Group {
    protected String name;
    protected Group parent;
    protected Set<Group> children = new HashSet<>();

    public abstract void setParent(Group parent) throws IllegalParentTypeException;

    public Group getParent() {
        return parent;
    }

    public Set<Group> getChildren() {
        return Collections.unmodifiableSet(children);
    }
    public void addChild(Group group) {
        this.children.add(group);
    }
    public void removeChild(Group group) {
        this.children.remove(group);
    }
    public void setName(String name) {this.name = name;}
    public String getName() {return name;}

    @Override
    public String toString() {
        return String.format("Group { Name: %s }", getName());
    }
}
