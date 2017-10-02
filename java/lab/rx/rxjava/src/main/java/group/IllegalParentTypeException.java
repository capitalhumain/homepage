package group;

public class IllegalParentTypeException extends Exception {
    public IllegalParentTypeException() {
        super("Illegal Parent Type Exception");
    }

    public IllegalParentTypeException(String msg) {
        super(msg);
    }
}
