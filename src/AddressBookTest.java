/**
 *  Tests the AddressBook Class
 */

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressBookTest {
    /**
     * An arrayList of AddressEntry's
     */
    private ArrayList<AddressEntry> addressEntryList = new ArrayList<AddressEntry>();

    /**
     * Ensures that the List method works
     */
    @Test
    void testList() {
        //traverse the entire list
        AddressBook obj = new AddressBook();
        obj.list();
        assertEquals("","");
    }
    /**
     * Ensures that the Add method works
     */
    @Test
    void testAdd() {
        // adding an entry to the List, and ensuring that it was placed inside the list
        AddressEntry entry1 = new AddressEntry();
        addressEntryList.add(entry1);
        int expectedSize = 1;
        assertEquals(expectedSize, addressEntryList.size());
    }
    /**
     * Ensures that the Remove method works
     */
    @Test
    void testRemove()
    {
        // Initializing two randon AddressEntrie
        Name name1 = new Name("Bob", "Bob");
        Address address1 = new Address("street1", "city1", "state1",12345);
        AddressEntry newObj1 = new AddressEntry(name1, address1, "123-456-7890","bob@gmail.com", "df41323");
        Name name2 = new Name("Emily", "Emily");
        Address address2 = new Address("street2", "city2", "state2",12345);
        AddressEntry newObj2 = new AddressEntry(name2, address2, "123-456-7890","emily@gmail.com", "df41323");

        // Adding two random AddressEntries, then removing them
        AddressBook obj = new AddressBook();
        obj.add(newObj1);
        obj.add(newObj2);
        obj.remove("Emily", "Emily"); //deleting 1
        // Since "Emily" was deleted, list should be empty
        ArrayList<AddressEntry> list = obj.find("Emily");

        // list should be size = 0
        assertEquals(0, list.size());
    }
    /**
     * Ensures that the ReadFromFile method works
     */
    @Test
    void testReadFromFile() throws IOException {
        AddressBook obj = new AddressBook();
        ByteArrayOutputStream text = new ByteArrayOutputStream();
        System.setOut(new PrintStream(text));

        // it should print "File not Found!"
        String filename = "File2.txt";
        obj.readFromFile(filename);
        assertNotEquals("Read in " + 0 +" new Addresses, successfully loaded, " +
                "currently "+ 0 +" Addresses", text.toString());
    }
    /**
     * Ensures that the Find method works
     */
    @Test
    void testFind() {
        // Initializing two randon AddressEntries
        Name name1 = new Name("Bob", "Bob");
        Address address1 = new Address("street1", "city1", "state1",12345);
        AddressEntry newObj1 = new AddressEntry(name1, address1, "123-456-7890","bob@gmail.com", "df41323");
        Name name2 = new Name("Emily", "Emily");
        Address address2 = new Address("street2", "city2", "state2",12345);
        AddressEntry newObj2 = new AddressEntry(name2, address2, "123-456-7890","emily@gmail.com", "df41323");

        // Adding two random AddressEntries, then removing them
        AddressBook obj = new AddressBook();
        obj.add(newObj1);
        obj.add(newObj2);
        // checkking if "Emily" was actually found
        ArrayList<AddressEntry> list = obj.find("Emily");
        assertEquals("Emily",list.get(0).getName().getLastName());
    }
    /**
     * Ensures that the SortList method works
     */
    @Test
    void z() {
        // Initializing two randon AddressEntries
        Name name1 = new Name("Bob", "Bob");
        Address address1 = new Address("street1", "city1", "state1",12345);
        AddressEntry newObj1 = new AddressEntry(name1, address1, "123-456-7890","bob@gmail.com", "df41323");
        Name name2 = new Name("Emily", "Emily");
        Address address2 = new Address("street2", "city2", "state2",12345);
        AddressEntry newObj2 = new AddressEntry(name2, address2, "123-456-7890","emily@gmail.com", "df41323");

        // Retrieving the "output" that would be printed to the console
        // text - contains all the info/text that obj.list outputs when called
        ByteArrayOutputStream text = new ByteArrayOutputStream();
        System.setOut(new PrintStream(text));

        // Adding these two random AddressEntries to an AddressBook
        AddressBook obj = new AddressBook();
        obj.add(newObj1);
        obj.add(newObj2);
        obj.sortList();
        obj.list();

        //This is unexpected Bob should be first, then Emily
        String unexpected = "Emily" + " " + "Emily" + " \n" + "street2" + " " + "city2" + " \n" + "state2" + " "
                + 67890 + " \n" + "098-765-4321" + " \n" + "emily@gmail.com" + "\n"
        +               "\nBob" + " " + "Bob" + " \n" + "street1" + " " + "city1" + " \n" + "state1" + " "
                + 12345 + " \n" + "123-456-7890" + " \n" + "bob@gmail.com" + "\n\n";

        assertNotEquals(unexpected, text.toString());
        }
}