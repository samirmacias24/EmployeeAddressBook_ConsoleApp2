import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * The Gui's main class creates an interface with basic operations
 * that can be done to a list such as: add, remove, exit, update
 * and search.
 * @author Samir Macias, Mark Perez, Megan Jen
 * @version 1.0
 */
public class GuiMain
{
    private JFrame frame;
    private JScrollPane scrollPane;
    // The Panels
    private JPanel MainPanel = new JPanel();
    private JPanel listPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JPanel searchPanel = new JPanel();

    // The five buttons
    private JButton newButton;
    private JButton removeButton;
    private JButton updateButton;
    private JButton exitButton;
    private JButton enterButton;

    // 9 entries from user
    private JPanel addingPanel = new JPanel();

    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;

    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;


    private JTextField textField1;
    private JLabel searchLabel;

    private ArrayList<AddressEntry> addressEntryList = new ArrayList<>();
    private DefaultListModel<AddressEntry> myaddressEntryListModel = new DefaultListModel<AddressEntry>();
    private JList <AddressEntry> addressEntryJList  = new JList<AddressEntry>(myaddressEntryListModel);

    private String searchText; // string will contain what the user entered to search

    AddressBook book = new AddressBook(); // will contain all the entries
    private boolean singleWindow; // ensures that only one additional window can be created
    /**
     * Constructor creates an interface with corresponding components
     * Add,Remove,Update,Search, Exit,
     */
    public GuiMain()
    {
        // calls 'dataBaseConnect' and retrieves all the entries from the database
        try {
            book = dataBaseConnect(); //places all the entries into 'book' an AddressBook
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        addressEntryList = book.list(); //add entries to addressEntry

        for(int i = 0; i<addressEntryList.size(); i++)
        {  this.myaddressEntryListModel.add(i, this.addressEntryList.get(i)); }

        //Now when we create our JList do it from our ListModel rather than our vector of AddressEntry

        // create JList using the addressEntryList
        this.addressEntryJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.addressEntryJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        this.addressEntryJList.setVisibleRowCount(-1);

        //create scrollPane associated with JList
        addressEntryJList.setSize(new Dimension(200,200));
        scrollPane = new JScrollPane(this.addressEntryJList,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        listPanel.add(scrollPane);

        //setup for adding new entries 'addingPanel'
        label2 = new JLabel("First Name");
        label3 = new JLabel("Last Name");
        label4 = new JLabel("Street");
        label5 = new JLabel("City");
        label6 = new JLabel("State");
        label7 = new JLabel("Zip");
        label8 = new JLabel("Email");
        label9 = new JLabel("Phone");
        label10 = new JLabel("ID");

        textField2 = new JTextField();
        textField2.setColumns(10);
        textField3= new JTextField();
        textField3.setColumns(10);
        textField4= new JTextField();
        textField4.setColumns(10);
        textField5= new JTextField();
        textField5.setColumns(10);
        textField6= new JTextField();
        textField6.setColumns(10);
        textField7= new JTextField();
        textField7.setColumns(10);
        textField8= new JTextField();
        textField8.setColumns(10);
        textField9= new JTextField();
        textField9.setColumns(10);
        textField10= new JTextField();
        textField10.setColumns(10);

        addingPanel.add(label2);
        addingPanel.add(textField2);
        addingPanel.add(label3);
        addingPanel.add(textField3);
        addingPanel.add(label4);
        addingPanel.add(textField4);
        addingPanel.add(label5);
        addingPanel.add(textField5);
        addingPanel.add(label6);
        addingPanel.add(textField6);
        addingPanel.add(label7);
        addingPanel.add(textField7);
        addingPanel.add(label8);
        addingPanel.add(textField8);
        addingPanel.add(label9);
        addingPanel.add(textField9);
        addingPanel.add(label10);
        addingPanel.add(textField10);


        // setup for the top panel 'searchPanel'
        textField1 = new JTextField();
        textField1.setColumns(15);              // gives text field enough room for user to input last name
        searchLabel = new JLabel("Search");
        enterButton = new JButton("Enter");
        searchPanel.add(searchLabel);
        searchPanel.add(textField1);
        searchPanel.add(enterButton);

        // initilizing buttons for 'buttonPanel'
        newButton = new JButton("Add");
        removeButton = new JButton("Remove");
        updateButton = new JButton("Update");
        exitButton = new JButton("Exit");
        // addding buttons
        buttonPanel.add(newButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(exitButton);

        MainPanel.add(addingPanel);
        MainPanel.add(searchPanel);
        MainPanel.add(buttonPanel);
        MainPanel.add(listPanel);

        frame = new JFrame("Project2");
        frame.getContentPane().add(MainPanel);
        frame.setBounds(100, 100, 600, 300);
        frame.getContentPane().setLayout(new GridLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        /**
         * Removes an entry from list and database
         */
        removeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0) {
                int index = addressEntryJList.getSelectedIndex();
                //must delete from the database
                try {
                    AddressEntry name = addressEntryJList.getModel().getElementAt(index); // get the entry we seek to delete
                    removeDataBaseInfo(name);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                // delete from JList
                if(index != -1)//something is selected otherwise do nothing
                {
                    // removing from: AddressBook , AddressEntryList, Model
                    book.remove(addressEntryList.get(index).getName().getLastName(),addressEntryList.get(index).getName().getFirstName());
                    addressEntryList.remove(index);
                    myaddressEntryListModel.removeElementAt(index);
                }
            }
        });
        //scrollPane.setColumnHeaderView(btnRemove);
        /**
         * Cretes a new 'searchText' object to open new window.
         * This window will display the list of names matching the
         * input from the user
         */
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame.setVisible(false);
                if(!singleWindow)
                {
                    //singleWindow = true;
                    searchText = textField1.getText(); // retrieve whatever the user entered
                    SearchEntryList newList = new SearchEntryList(searchText);
                }
            }
        });
        /**
         *  Exits the program
         */
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        /**
         *  Adds new AddressEntry to the database and list
         */
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = textField2.getText();
                String b = textField3.getText();
                String c = textField4.getText();
                String d = textField5.getText();
                String ee = textField6.getText();
                String f =  textField7.getText();
                String g = textField8.getText();
                String h = textField9.getText();
                String i = textField10.getText();

                int ff =Integer.parseInt(f);
                AddressEntry newEntry = new AddressEntry(a,b,c,d,ee,ff,g,h,i);

                book.add(newEntry);
                book.sortList();
                addressEntryList = book.list(); // the ArrayList gets the new organized list

                // adding to the database
                try {
                    addDataBaseInfo(newEntry);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

                myaddressEntryListModel.clear();
                for(int j = 0; j<addressEntryList.size(); j++)
                {
                    myaddressEntryListModel.add(j, addressEntryList.get(j));
                }
                // create JList using the addressEntryList
                addressEntryJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                addressEntryJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
                addressEntryJList.setVisibleRowCount(-1);

                // will clear all the entries entered
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
                textField7.setText("");
                textField8.setText("");
                textField9.setText("");
                textField10.setText("");

            }
        });
        /**
         * Will update an entry from the list
         */
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = addressEntryJList.getSelectedIndex(); // retrieve selected index
                AddressEntry original  = addressEntryJList.getModel().getElementAt(index);

                // will remove selected entry from AddressBook , AddressEntryList, Model
                if(index != -1)//something is selected otherwise do nothing
                {
                    // removing from: AddressBook , AddressEntryList, Model
                    book.remove(addressEntryList.get(index).getName().getLastName(),addressEntryList.get(index).getName().getFirstName());
                    addressEntryList.remove(index);
                    myaddressEntryListModel.removeElementAt(index);
                }
                else // if nothing is selecteed then don't do anything
                {
                    return;
                }
                    //creating a new entry from user input
                    String a = textField2.getText();
                    String b = textField3.getText();
                    String c = textField4.getText();
                    String d = textField5.getText();
                    String ee = textField6.getText();
                    String f = textField7.getText();
                    String g = textField8.getText();
                    String h = textField9.getText();
                    String i = textField10.getText();
                    int ff = Integer.parseInt(f);
                    AddressEntry newEntry = new AddressEntry(a, b, c, d, ee, ff, g, h, i);

                try {
                    updateDataBaseInfo(original, newEntry);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                book.add(newEntry);
                book.sortList();
                addressEntryList = book.list(); // the ArrayList gets the new organized list

                myaddressEntryListModel.clear();
                for (int j = 0; j < addressEntryList.size(); j++) {
                    myaddressEntryListModel.add(j, addressEntryList.get(j));
                }
                // create JList using the addressEntryList
                addressEntryJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                addressEntryJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
                addressEntryJList.setVisibleRowCount(-1);

                // will clear all the entries entered
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
                textField7.setText("");
                textField8.setText("");
                textField9.setText("");
                textField10.setText("");


            }
        });
    }
    /**
     * Creates the UI
     * @param args not used
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run() {
                try {
                    GuiMain window = new GuiMain();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * Connects the database and takes all the information and
     * places it into an AddressBook
     * @return AddressBook with all the entries in the database
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static AddressBook dataBaseConnect() throws SQLException, ClassNotFoundException
    {
        AddressBook newAdrBook = new AddressBook();
        // Load the Oracle JDBC driver
        Class.forName ("oracle.jdbc.OracleDriver"); //name of driver may change w/ versions
        // Connect to the database
        Connection conn =
                DriverManager.getConnection("jdbc:oracle:thin:mcs1021/qeDnwqhU@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");

        // Create a Statement
        Statement stmt = conn.createStatement ();
        // Select the all (*) from the table JAVATEST
        ResultSet rset = stmt.executeQuery("SELECT * FROM ADDRESSENTRYTABLE");
        System.out.println(rset);

        // Iterate through the result and print the employee names
        while (rset.next ()) //get next row of table returned
        {
            String id = null;
            String firstname = null;
            String lastName = null;
            String state = null;
            String city = null;
            String street = null;
            String zip = null;
            String email = null;
            String phone =null;
            for(int i=1; i<=rset.getMetaData().getColumnCount(); i++) //visit each column
            {
                String val = rset.getString(i);
                switch(i)
                {
                    case 1:
                        id = val;
                        break;
                    case 2:
                        firstname = val;
                        break;
                    case 3:
                        lastName = val;
                        break;
                    case 4:
                        street = val;
                        break;
                    case 5:
                        city = val;
                        break;
                    case 6:
                        state = val;
                        break;
                    case 7:
                        zip = val;
                        break;
                    case 8:
                        email = val;
                        break;
                    case 9:
                        phone = val;
                        break;
                }
            }
            int newZip = Integer.parseInt(zip);
            AddressEntry newEntry = new AddressEntry(firstname, lastName, street, city, state, newZip,email, phone,id);
            newAdrBook.add(newEntry);
        }
        newAdrBook.sortList();
        //Close access to everything...will otherwise happen when disconnect
        // from database.
        rset.close();
        stmt.close();
        conn.close();

        return newAdrBook;
    }

    /**
     * Reads every entry in the database, deletes/removes the one mathcing 'name' parameter
     * @param name the lastName of the entry that is going to be deleted(removed from database
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void removeDataBaseInfo(AddressEntry name) throws SQLException, ClassNotFoundException
    {
        // Load the Oracle JDBC driver
        Class.forName ("oracle.jdbc.OracleDriver"); //name of driver may change w/ versions
        // Connect to the database
        Connection conn =
                DriverManager.getConnection("jdbc:oracle:thin:mcs1021/qeDnwqhU@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");

        // Create a Statement
        Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_UPDATABLE);

        // Select the all (*) from the table JAVATEST
        ResultSet rset = stmt.executeQuery("SELECT * FROM ADDRESSENTRYTABLE");
        System.out.println(rset);

        // Check ResultSet's updatability
        if (rset.getConcurrency() == ResultSet.CONCUR_READ_ONLY) {
            System.out.println("ResultSet non-updatable.");
        } else {
            System.out.println("ResultSet updatable.");
        }
        // Iterate through the result and print the employee names
        while (rset.next ()) //get next row of table returned
        {
            String id = null;
            String firstname = null;
            String lastName = null;
            String state = null;
            String city = null;
            String street = null;
            String zip = null;
            String email = null;
            String phone =null;
            for(int i=1; i<=rset.getMetaData().getColumnCount(); i++) //visit each column
            {
                String val = rset.getString(i);
                switch(i)
                {
                    case 1:
                        id = val;
                        break;
                    case 2:
                        firstname = val;
                        break;
                    case 3:
                        lastName = val;
                        break;
                    case 4:
                        street = val;
                        break;
                    case 5:
                        city = val;
                        break;
                    case 6:
                        state = val;
                        break;
                    case 7:
                        zip = val;
                        break;
                    case 8:
                        email = val;
                        break;
                    case 9:
                        phone = val;
                        break;
                }
            }
            //creates a new AddressEntry
            int newZip = Integer.parseInt(zip);
            AddressEntry newEntry = new AddressEntry(firstname, lastName, street, city, state, newZip,email, phone,id);
            int new_id = Integer.parseInt(newEntry.getID());
            //if the addressEntry's lastName matches given argument then delete it (using its ID)
            if((name.getName().getLastName()).equals(newEntry.getName().getLastName()) && name.getName().getFirstName().equals(newEntry.getName().getFirstName()))
            {
                String sqlDelete = "DELETE FROM ADDRESSENTRYTABLE WHERE ID = " + new_id;
                System.out.println("The SQL statement is: " + sqlDelete + "\n");//for debugging
                int countDeleted = stmt.executeUpdate(sqlDelete);
                System.out.println(countDeleted + " records deleted.\n");
                break;
            }
        }
        //Close access to everything...will otherwise happen when disconnect
        // from database.
        rset.close();
        stmt.close();
        conn.close();
    }

    /**
     * Adds a new Entry to the database
     * @param newEntry is the new entry that is going to be added
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void addDataBaseInfo(AddressEntry newEntry) throws SQLException, ClassNotFoundException
    {
        // Load the Oracle JDBC driver
        Class.forName ("oracle.jdbc.OracleDriver"); //name of driver may change w/ versions
        //Connecting to DataBase
        Connection conn =
                DriverManager.getConnection("jdbc:oracle:thin:mcs1021/qeDnwqhU@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");
        String query = "delete from users where LASTNAME = ?";
        // Create a Statement
        Statement stmt = conn.createStatement();

        String id = newEntry.getID();
        int id2 = Integer.parseInt(id);
        String firstName = newEntry.getName().getFirstName();
        String lastName = newEntry.getName().getLastName();
        String street = newEntry.getAddress().getStreet();
        String city = newEntry.getAddress().getCity();
        String state = newEntry.getAddress().getState();
        int zip = newEntry.getAddress().getZip();
        String email = newEntry.getEmail();
        String phone = newEntry.getPhone();

        // sets up the SQL statement
        String sqlInsert = "INSERT INTO ADDRESSENTRYTABLE VALUES " + "(" + id2 +
                ", '"+firstName + "', '"+ lastName+"', '"+street+"', '"+ city+"', '"+ state+
                "', "+ zip+", '"+ email+"', '" + phone + "')";

        // Adds the new Entry to the database
        System.out.println("The SQL statement is: " + sqlInsert + "\n");  // Echo for debugging
        int countInserted = stmt.executeUpdate(sqlInsert);
        System.out.println(countInserted + " records inserted.\n");

        stmt.close();
        conn.close();
    }

    /**
     * updates one of the entries
     * @param newEntry1 original entry
     * @param newEntry2 the entry that will replace the original
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void updateDataBaseInfo(AddressEntry newEntry1, AddressEntry newEntry2) throws SQLException, ClassNotFoundException
    {
        removeDataBaseInfo(newEntry1);
        addDataBaseInfo(newEntry2);
    }

    // Setters and Getters
    public void setSearchText(String text)
    {
        searchText = text;
    }
    public String getSearchText()
    {
        return searchText;
    }
    public void setList(ArrayList<AddressEntry> list)
    {
        addressEntryList = list;
    }
    public ArrayList<AddressEntry> getList()
    {
        return addressEntryList;
    }
    public void setAddressBook(AddressBook someBook)
    {
        book = someBook;
    }
    public AddressBook getAddressBook()
    {
        return book;
    }
    public void setSingleWindow(boolean a)
    {
        singleWindow = a;
    }
    public boolean getSingleWindow()
    {
        return singleWindow;
    }

}
