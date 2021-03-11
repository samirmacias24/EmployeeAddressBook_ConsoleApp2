/**
 * Tests the AddressEntry Class
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddressEntryTest {
    /**
     * Creates a quick check to see if any AddressEntry that uses
     * toString() is formatted correctly
     */
    @Test
    void testToString() {
        // creating a generic AddressEntry
        Name name = new Name("Bob", "Bob");
        Address address = new Address("street1", "city1", "state1",12345);
        AddressEntry newEntry = new AddressEntry(name, address, "123-456-7890","bob@gmail.com", "df41323");

        // Compare to see if "output" is formatted as it should
        String output = newEntry.toString();
        String expectedOutput = "Bob" + " " + "Bob" + " \n" + "street1" + " " + "city1" + " \n" + "state1" + " "
                + 12345 + " \n" + "123-456-7890" + " \n" + "bob@gmail.com" + "\n";
        assertEquals(expectedOutput, output);
    }

}