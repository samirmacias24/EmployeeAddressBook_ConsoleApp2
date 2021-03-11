import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
/**
 * Creates a List of AddressEntry objects. Is able to print everything
 * contained in the list. As well as add as many AddressEntry objects
 * as needed.
 */
public class AddressBook
{
    /**
     * An arrayList of AddressEntry's
     */
    private ArrayList<AddressEntry> addressEntryList;
    /**
     * Initializes the addressBook's arrayList
     */
    AddressBook()
    {
        addressEntryList = new ArrayList<AddressEntry>();
    }
    /**
     * Cycles through the collection of AddressEntry objects, contained in AddressBook's list.
     * Prints all the information for each AddressEntry.
     */
    public ArrayList<AddressEntry> list()
    {
        ArrayList<AddressEntry> arr = new ArrayList<>();
        //traverse the entire list
        for(int i = 0; i < addressEntryList.size(); i++)
        {
            arr.add(addressEntryList.get(i));
            System.out.println(addressEntryList.get(i).toString());
        }
        return arr;
    }
    /**
     * Adds an AddressEntry object into the list
     * @param obj is an AddressEntry that will be added to the AddressBook list
     */
    public void add(AddressEntry obj)
    {
        addressEntryList.add(obj);
    }

    /**
     * Removes an Address Entry object from the list
     * @param lastname is a String value used to find a specfic AddressEntry
     */
    public void remove(String lastname, String firstname)
    {
        for(int i = 0; i < addressEntryList.size(); i++)
        {
            String realLastName = addressEntryList.get(i).getName().getLastName();
            String realFirstName = addressEntryList.get(i).getName().getLastName();
            if(realFirstName == firstname && realLastName == lastname)
            {
                addressEntryList.remove(i);
                break;
            }
        }
    }

    /**
     * Takes in a file name, used to open a specfic file
     * Tells the user to try again if file not found
     * @param filename is the name of a File
     */
    public void readFromFile(String filename)
    {
        int counter = 0; // counts the number of indidviduals in the file
        File file = new File(filename);
        // checks if file exists
        if(file.exists())
        {
            try {
                Scanner fileInput = new Scanner(file);
                while(fileInput.hasNext()) // traverse through each line
                {
                    String firstName = fileInput.nextLine();
                    String lastName = fileInput.nextLine();
                    String street = fileInput.nextLine();
                    String city = fileInput.nextLine();
                    String state = fileInput.nextLine();
                    String zip = fileInput.nextLine();
                    String phone = fileInput.nextLine();
                    String email = fileInput.nextLine();
                    String id = fileInput.nextLine();

                    int zip2 = Integer.parseInt(zip);


                    Name name = new Name(firstName, lastName);
                    Address address = new Address(street,city,state,zip2);
                    AddressEntry info = new AddressEntry(name, address, phone, email, id);
                    addressEntryList.add(info);
                    counter++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Read in " + counter +" new Addresses, successfully loaded, " +
                    "currently "+ counter +" Addresses");
        }
        else
        {
            System.out.println("File not Found!");
        }
    }

    /**
     *
     * @param lastName is an addressEntry's last name
     */
    public LinkedList<AddressEntry> find(String lastName)
    {
        int val = 1;
        LinkedList<AddressEntry> obj = new LinkedList<>();
        boolean sameLastName = false;// will allow us to know if string value entered is found
        for(int i = 0; i < addressEntryList.size(); i++)
        {
            // contains the actual last name of that index
            String actualLastName = addressEntryList.get(i).getName().getLastName();

            // input is compared char by char, to find any entry that has the
            // same beginning of last name
            for(int j = 0; j < lastName.length(); j++)
            {
                // if any character is not the name, then "sameLastName" becomes
                // false and we break out of the loop
                if(lastName.charAt(j) != actualLastName.charAt(j))
                {
                    sameLastName = false;
                    break;
                }
                sameLastName = true;
            }
            // if "sameLastName" is actually the same or the beginning is the same
            // then we will print it out
            // And places that AddressEntry to the new list
            if(sameLastName)
            {
                System.out.print(val + ".) ");
                val++;
                System.out.println(addressEntryList.get(i).toString());
                obj.add(addressEntryList.get(i));
            }
        }
        return obj;
    }
    /**
     * It sorts the list in alphabetical order by last names
     */
    public void sortList()
    {
        Collections.sort(addressEntryList);
    }
}