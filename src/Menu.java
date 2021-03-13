import java.util.*;
import java.io.*;
/**
 * Allows user to print specific phrases. Each of these
 * phrases represent a private variable from the AddressEntry
 * class
 */
public class Menu  {

    static Scanner input = new Scanner(System.in);
    /**
     * Loads entries from a file
     */
    public void loadEntries(AddressBook obj)
    {
        int counter = 0; // counts the number of indidviduals in the file

        // retrieve file name from user
        String fileName;
        System.out.println("Enter a FileName: ");
        fileName = input.nextLine();

        //reads text from file
        obj.readFromFile(fileName);
    }
    /**
     *
     * Will prompt the user for information to be used to create
     * a new AddressEntry
     */
    public void addition(AddressBook obj)
    {
        // prompting the user to enter the necessary info

        String firstName = prompt_FirstName();
        String lastName = prompt_LastName();
        String street = prompt_Street();
        String city = prompt_City();
        String state = prompt_State();
        int zip = prompt_Zip();
        String phoneNum = prompt_Telephone();
        String email = prompt_Email();
        String ID = prompt_id();

        AddressEntry newPerson = new AddressEntry(firstName, lastName,street,city,state,zip,phoneNum, email, ID);
        // initializing a newPerson obj with the user's input
        obj.add(newPerson);
        // displays the added contact
        System.out.println("Thank you the following contact has been added to your address book: ");
    }

    /**
     * Removal of an AddressEntry from the AddressBook
     * Where the specific object is found first, then the
     * user selects from the find results what to remove
     */
    public void removal(AddressBook obj)
    {
        System.out.println("Enter the Last Name of contact to remove: ");
        String name = input.nextLine(); // retrieve last name of user we wish to remove

        System.out.println("The following entries where found in the address book" +
                ", select number of entry you wish to remove: ");

        //a find is done, prints out all the people with that last name
        ArrayList<AddressEntry> list; // list will have all the possible people to delete
        list = obj.find(name);  //LIST - contains list of possible AddressEntries to delete

        // Checking to see if there is one AddressEntry close to what they seek
        // if not there is not need to ask user for an input
        if(list.size() >= 1)
        {
            // get value from user of the AddressEntry they want to delete
            // do while ensures that value entered is within the range of available options
            int val;
            do {
             String optionWantToDelete = input.nextLine(); // user input
             val = Integer.parseInt(optionWantToDelete);
            }
            while (list.size() < val || val <= 0);

            // Asking User if they "really" want to delete the AddressEntry
            System.out.println("Hit 'y' to remove the following entry or 'n' to return to main menu");
            AddressEntry a = list.get(val - 1);
            String usersOptionToDelete;
            a.toString();
            // ensure that the value entered by user is either 'y' or 'n'
            do
            {
                usersOptionToDelete = input.nextLine();
            }while((!usersOptionToDelete.equals("y")) &&  (!usersOptionToDelete.equals("n")));

            // if user wants to delete entry
            if(usersOptionToDelete.equals("y"))
            {
                //getting that specific AddressEntry and deleting it
                AddressEntry b = list.get(val - 1);
                obj.remove(b.getName().getLastName(), b.getName().getLastName());
                System.out.println("Removed!");
            }
            // if user seleteced 'n', then this prints
            else
            {
                System.out.println("No item was removed!");
            }
        }
        // if no entries are found then this prints
        else
        {
            System.out.println("\nNo entries found!");
        }
    }
    /**
     * Will ask the user for a specfic last name, if only the
     * beginning of the last name is entered then it will display
     * any AddressEntry's whose last name is close to what was entered
     */
    public void find(AddressBook obj)
    {
        System.out.println("Enter in all or the beginning of the Last Name of the contact you wish to find: ");
        String name = input.nextLine();

        System.out.printf("%50s\n%15s\n","The following entries were found in the address book for a last name starting with", name);
        obj.find(name);
    }

    /**
     * Lists each AddressEntry's address in alphabetical order by
     * their last name
     */
    public void listing(AddressBook obj)
    {
        obj.sortList(); // first sorts the list
        obj.list(); // then it prints the list
    }

    /**
     * Quits the program
     */
    public void quit()
    {
        System.out.println("Goodbye!");
        System.exit(1);
    }

    /**
     * ALlows user to enter their first name
     * @return user's first name
     */
    public static String prompt_FirstName()
    {
        System.out.println("First Name:");
        String val = input.nextLine();
        return val;
    }

    /**
     * ALlows user to enter their last name
     * @return user's last name
     */
    public static String prompt_LastName()
    {
        System.out.println("Last Name:");
        String val = input.nextLine();
        return val;
    }

    /**
     * ALlows user to enter their street
     * @return user's street
     */
    public static String prompt_Street()
    {
        System.out.println("Street:");
        String val = input.nextLine();
        return val;
    }

    /**
     * ALlows user to enter their city
     * @return user's city
     */
    public static String prompt_City()
    {
        System.out.println("City:");
        String val = input.nextLine();
        return val;
    }

    /**
     * ALlows user to enter their state
     * @return user's state
     */
    public static String prompt_State()
    {
        System.out.println("State:");
        String val = input.nextLine();
        return val;
    }

    /**
     * ALlows user to enter their zip
     * @return user's zip
     */
    public static int prompt_Zip()
    {
        System.out.println("Zip:");
        String val = input.nextLine();
        int value = Integer.parseInt(val);
        return value;
    }

    /**
     * ALlows user to enter their telephone number
     * @return user's telephone number
     */
    public static String prompt_Telephone()
    {
        System.out.println("Telephone:");
        String val = input.nextLine();
        return val;
    }

    /**
     * ALlows user to enter their email
     * @return user's email
     */
    public static String prompt_Email()
    {
        System.out.println("Email:");
        String val = input.nextLine();
        return val;
    }
    /**
     * ALlows user to enter their ID
     * @return user's ID
     */
    public static String prompt_id()
    {
        System.out.println("ID:");
        String val = input.nextLine();
        return val;
    }
}
