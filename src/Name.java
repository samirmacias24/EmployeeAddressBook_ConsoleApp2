/**
 * Defines a individual's name including: First Name, Last Name
 */
public class Name {
    /**
     * The individual's first name
     */
    private String firstName;
    /**
     * The individual's last name
     */
    private String lastName;

    public Name(String a, String b) {
        firstName = a;
        lastName = b;
    }
    /**
     * Returns the full name of an Entry
     * @return the full name of a given Entry
     */
    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
