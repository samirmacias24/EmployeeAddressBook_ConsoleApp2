/**
 * Contains the basic information for any individual
 */
public class AddressEntry implements Comparable<AddressEntry>
{
    /**
     * Name obj, contains full name
     */
    private Name name;
    /**
     * Address obj, contains the entire address
     */
    private Address address;
    /**
     * The individual's phone number
     */
    private String phone;
    /**
     * The individual's personal email
     */
    private String email;
    /**
     * Personal ID
     */
    private String ID;

    AddressEntry()
    {
        name.setFirstName("");
        name.setLastName("");
        address.setCity("");
        address.setState("");
        address.setStreet("");
        address.setZip(0);
        phone = "";
        email = "";
        ID = "";
    }
    /**
     * Initializes all the information for an individual
     * @param firstname is persons first name
     * @param lastname is person's last name
     * @param theStreet is person's street address
     * @param theCity is person's city
     * @param theState is person's state
     * @param theZip is person's zip
     * @param thePhone is person's phone number
     * @param theEmail is person's personal email
     */
    AddressEntry(String firstname, String lastname, String theStreet, String theCity,
                 String theState, int theZip, String thePhone, String theEmail, String id)
    {
        name = new Name(firstname, lastname);
        address = new Address(theStreet, theCity, theState, theZip);
        phone = thePhone;
        email = theEmail;
        ID = id;
    }
    /**
     * Initializes all the information for an individual
     * @param theName is an object containing person's full name
     * @param theAddress is an object containing person's address
     * @param thePhone is person's phone number
     * @param theEmail is person's personal email
     * @param id is person's personal ID
     */
    AddressEntry(Name theName, Address theAddress, String thePhone, String theEmail, String id)
    {
        name = theName;
        address = theAddress;
        phone = thePhone;
        email = theEmail;
        ID = id;
    }
    /**
     * used to compare AddressEntries in order to be able to sort them
     * in alphabetical order
     * @param obj any AddressEntry object within an AddressBook
     */
    public int compareTo(AddressEntry obj)
    {

        return this.getName().getLastName().compareToIgnoreCase(obj.getName().getLastName());
       // return this.getLastName().compareToIgnoreCase(obj.getLastName());
    }
    /**
     * Formats the individuals information properly, ready to be printed
     * @return a String containing the individuals information well formatted
     */
    @Override
    public String toString() {
        return "AddressEntry{" +
                "name=" + name.toString() +
                ", address=" + address.toString()+
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }

    /**
     * Returns Name object
     * @return a Name object
     */
    public Name getName() {
        return name;
    }
    /**
     * Sets the name of the individual
     * @param name is the Name object
     */
    public void setName(Name name) {
        this.name = name;
    }
    /**
     * Returns Address object
     * @return the specific address
     */
    public Address getAddress() {
        return address;
    }
    /**
     * Sets the name of the individual
     * @param address is the person's phone
     */
    public void setAddress(Address address) {
        this.address = address;
    }
    /**
     * Returns String of id
     * @return the specific ID
     */
    public String getID() {
        return ID;
    }
    /**
     * Sets the ID of the individual
     * @param ID is the person's personal ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }
    /**
     * Sets the phone of the individual
     * @param thePhone is the person's phone
     */
    public void setPhone(String thePhone)
    {
        phone = thePhone;
    }

    /**
     * Returns String value of the phone number
     * @return the phone number
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * Sets the email of the individual
     * @param theEmail is the person's email
     */
    public void setEmail(String theEmail)
    {
        email = theEmail;
    }

    /**
     * Returns String value of the email
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

}
