package group;

public class DepGroup extends Group {

    public void setParent(Group parent) throws IllegalParentTypeException {
        if(parent instanceof UserGroup) {
            throw new IllegalParentTypeException();
        }
        if(this.parent != null) {
            this.parent.removeChild(this);
        }
        this.parent = parent;
        if(parent != null) {
            this.parent.addChild(this);
        }
    }
}
