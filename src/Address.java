/**
 * Defines an address including: state, city, street, zip
 */
public class Address {
    /**
     * The individual's street
     */
    private String street;
    /**
     * The individual's city
     */
    private String city;
    /**
     * The individual's state
     */
    private String state;
    /**
     * The individual's zip
     */
    private int zip;

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                '}';
    }

    /**
     * Returns the address of an Entry
     * @return the address of a given Entry
     */
    Address(String street1, String city1, String state1, int zip1)
    {
        street = street1;
        city = city1;
        state = state1;
        zip = zip1;
    }

    //Setters and Getters
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
}
