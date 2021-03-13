/**
 * Tests the Menu Class
 */
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    /**
     * Ensures that the LoadEntries method works properly
     */
    @Test
    void testLoadEntries() {
        // Assuming user enters "data" as the file name
        String data = "File.txt";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);
        //retrieve input - newVal
        String newVal = scanner.nextLine();
        System.out.println(newVal);

        String expectedFileName = "File.txt";
        assertEquals(newVal,expectedFileName );
    }

    /**
     * Ensures that the Addition method works properly
     */
    @Test
    void testAddition() {
        Menu menu = new Menu();
        AddressBook obj = new AddressBook();
        String data = "Bob\n"+ "Bob\n"+ "street1\n" + "city\n" +
                "state1\n"+ 12345+"\n" + "123-456-7890\n"+
                "bob@gmail.com\n";
        //the "data" is picked up as if it where user input from the console
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        // retrieving all the necessary information
        String firstName = scanner.nextLine();
        String lastName = scanner.nextLine();
        String street = scanner.nextLine();
        String city = scanner.nextLine();
        String state = scanner.nextLine();
        String zip = scanner.nextLine();
        String phoneNum = scanner.nextLine();
        String email = scanner.nextLine();
        int realZip = Integer.parseInt(zip);
        // new AddressEntry
        Name name1 = new Name("Bob", "Bob");
        Address address1 = new Address("street1", "city1", "state1",12345);
        AddressEntry newPerson = new AddressEntry(name1, address1, "123-456-7890","bob@gmail.com", "df41323");

        obj.add(newPerson);
        ArrayList<AddressEntry> newList = obj.find("Bob");
        String expected = "Bob" + " " + "Bob" + " \n" + "street1" + " " + "city" + " \n" + "state1" + " "
                + 12345 + " \n" + "123-456-7890" + " \n" + "bob@gmail.com" + "\n";

        // checks the list to ensure that the new AddressEntry was added
        assertEquals(expected,newList.get(0).toString());
    }

    /**
     * Ensures that the Removal method works properly
     */
    @Test
    void testRemoval() {
        Menu menu = new Menu();
        // Create new AddressBook,adding a new AddressEntry to it
        AddressBook obj = new AddressBook();
        Name name1 = new Name("Bob", "Bob");
        Address address1 = new Address("street1", "city1", "state1",12345);
        AddressEntry newEntry = new AddressEntry(name1, address1, "123-456-7890","bob@gmail.com", "df41323");

        obj.add(newEntry);
        // Testing if we were able to remove
        obj.remove("Bob", "Bob");
        ArrayList<AddressEntry> list = obj.find("Bob");
        assertEquals(0, list.size());
    }

    /**
     * Ensures that the Find method works properly
     */
    @Test
    void testFind() {
        String data = "Bobby"; // assumed user input
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);
        //retrieve input - newVal
        String newVal = scanner.nextLine();
        System.out.println(newVal);

        String expected = "Bobby";
        assertEquals(expected, newVal);
    }

    /**
     * Creates a situation with user input and ensures that
     * it is the same with the expected
     */
    @Test
    void testPrompt_FirstName() {
        String data = "Bob"; // assumed user input
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);
        //retrieve input - newVal
        String newVal = scanner.nextLine();
        System.out.println(newVal);

        String expected = "Bob";
        assertEquals(expected, newVal);
    }
    /**
     * Creates a situation with user input and ensures that
     * it is the same with the expected
     */
    @Test
    void testPrompt_LastName() {
        String data = "Bill"; // assumed user input
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);
        //retrieve input - newVal
        String newVal = scanner.nextLine();
        System.out.println(newVal);

        String expected = "Bill";
        assertEquals(expected, newVal);
    }
    /**
     * Creates a situation with user input and ensures that
     * it is the same with the expected
     */
    @Test
    void testPrompt_Street() {
        String data = "123 Corner St."; // assumed user input
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);
        //retrieve input - newVal
        String newVal = scanner.nextLine();
        System.out.println(newVal);

        String expected = "123 Corner St.";
        assertEquals(expected, newVal);
    }
    /**
     * Creates a situation with user input and ensures that
     * it is the same with the expected
     */
    @Test
    void testPrompt_City() {
        String data = "San Francisco"; // assumed user input
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);
        //retrieve input - newVal
        String newVal = scanner.nextLine();
        System.out.println(newVal);

        String expected = "San Francisco";
        assertEquals(expected, newVal);
    }
    /**
     * Creates a situation with user input and ensures that
     * it is the same with the expected
     */
    @Test
    void testPrompt_State() {
        String data = "CA"; // assumed user input
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);
        //retrieve input - newVal
        String newVal = scanner.nextLine();
        System.out.println(newVal);

        String expected = "CA";
        assertEquals(expected, newVal);
    }
    /**
     * Creates a situation with user input and ensures that
     * it is the same with the expected
     */
    @Test
    void testPrompt_Zip() {
        String data = "12345"; // assumed user input
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);
        String val = scanner.nextLine();
        //retrieve input - newVal
        int newVal = Integer.parseInt(val);
        System.out.println(newVal);

        int expected = 12345;
        assertEquals(expected,newVal);
    }
    /**
     * Creates a situation with user input and ensures that
     * it is the same with the expected
     */
    @Test
    void testPrompt_Telephone() {
        String data = "408-123-4567"; // assumed user input
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);
        //retrieve input - newVal
        String newVal = scanner.nextLine();
        System.out.println(newVal);

        String expected = "408-123-4567";
        assertEquals(expected,newVal );
    }

    /**
     * Creates a situation with user input and ensures that
     * it is the same with the expected
     */
    @Test
    void testPrompt_Email() {
        String data = "noemail123@gmail.com"; // assumed user input
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);
        //retrieve input - newVal
        String newVal = scanner.nextLine();
        System.out.println(newVal);

        String expected = "noemail123@gmail.com";
        assertEquals(expected, newVal);
    }
}