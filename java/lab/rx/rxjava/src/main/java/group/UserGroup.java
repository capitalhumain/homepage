package group;

public class UserGroup extends Group {

    public void setParent(Group parent) throws IllegalParentTypeException {
        if(parent == null) {
            throw new IllegalArgumentException("parent cannot be null");
        }
        if(parent instanceof UserGroup) {
            throw new IllegalParentTypeException();
        }
        if(this.parent != null) {
            this.parent.removeChild(this);
        }
        this.parent = parent;
        this.parent.addChild(this);
    }
}
