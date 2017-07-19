package hello;

import org.springframework.data.annotation.Id;

public class Customer {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private int privilege;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(String firstName, String lastName, int privilege) {
        this(firstName, lastName);
        this.privilege = privilege;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%s, firstName=%s, lastName=%s, privilege=%d]", id, firstName, lastName, privilege);
    }
}
