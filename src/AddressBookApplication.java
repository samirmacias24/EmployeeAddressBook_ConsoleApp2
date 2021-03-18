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
}
