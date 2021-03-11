import java.util.*;
import java.io.*;

/**
 * Creates an AddressBook, a list of AddressEntry objects. Where each
 * AddressEntry is specific information about an individual.
 * @author Samir Macias
 * @version 1.0
 * @since January 28, 2021
 *
 */
public class AddressBookApplication {

    /**
     * The main method creates an AddressBook object, calls
     * functions to test its capabilities
     * @param args not used
     */

    public static void main(String [] args)
    {
        AddressBook obj = new AddressBook();
        mainMenu(obj);
    }

    /**
     * This method ensures that the user has a user interface.
     * It uses a switch statement that remains within a main menu
     * with multiple options
     * @param obj is an AddressBook object
     */
    static void mainMenu(AddressBook obj)
    {
        Menu menu = new Menu();
        Scanner input = new Scanner(System.in);
        System.out.println("---------------------------------------");
        System.out.println("Please enter in your menu selection");
        System.out.println("a) Loading From File");
        System.out.println("b) Addition");
        System.out.println("c) Removal");
        System.out.println("d) Find");
        System.out.println("e) Listing\n");

        System.out.println("f) Quit");
        System.out.println("---------------------------------------\n");

        String enteredChar = input.nextLine();
        //char enteredChar = val.charAt(0);
        switch(enteredChar)
        {
            case "a":
                menu.loadEntries(obj);
                mainMenu(obj);
                break;
            case "b":
                menu.addition(obj);
                mainMenu(obj);
                break;
            case "c":
                menu.removal(obj);
                mainMenu(obj);
                break;
            case "d":
                menu.find(obj);
                mainMenu(obj);
                break;
            case "e":
                menu.listing(obj);
                mainMenu(obj);
                break;
            case "f":
                menu.quit();
                break;
            default:
                mainMenu(obj);
                break;
        }
    }


    /**
     * Takes an AddressBook object and creates two AddressEntries. These Address Entries are
     * initialized with specific values. Then they are added to the AddressBook, which contains
     * a list of them. Lastly, the AddressBook object "ab", will have the list print.
     * @param ab is the AdressBook that will contain all the contacts
     */
    /*
    static void intiAddressBookExcercise(AddressBook ab)
    {
        AddressEntry obj1 = new AddressEntry("Bob", "Bob", "street1", "city1",
                "state1",12345, "123-456-7890"
                ,"bob@gmail.com");
        AddressEntry obj2 = new AddressEntry("Emily", "Emily", "street2", "city2",
                "state2",67890, "098-765-4321   "
                ,"emily@gmail.com");
        ab.add(obj1);
        ab.add(obj2);

        ab.list();
    }
*/
    /**
     * Takes a String value containing the name of a file we want to open.
     * It checks if the file exist and proceeds to read every line. If the
     * file contains the proper information, then it places it into an
     * address book object("obj"). After processing entire file it print
     * all the proper information it found.
     * @param filename is the name of a specific file we wish to open
     */
    /*
    static void init(String filename)
    {
        // invokes file (no assumed size of file)
        AddressBook obj = new AddressBook();
        File file = new File(filename);

        if(file.exists())
        {
            try {
                Scanner input = new Scanner(file);

                while(input.hasNext())
                {
                    String firstName = input.nextLine();
                    String lastName = input.nextLine();
                    String street = input.nextLine();
                    String city = input.nextLine();
                    String state = input.nextLine();
                    String zip = input.nextLine();
                    String phone = input.nextLine();
                    String email = input.nextLine();
                    int zip2 = Integer.parseInt(zip);
                    AddressEntry info = new AddressEntry(firstName, lastName, street, city, state, zip2, phone, email);
                    obj.add(info);
                }
                obj.list();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else
        {
            System.out.println("File not found!");
        }
    }
*/
}
