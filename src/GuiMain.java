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
 *
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

    private JTextField textField1;
    private JLabel searchLabel;

    private ArrayList<AddressEntry> addressEntryList = new ArrayList<>();
    private JList <AddressEntry> addressEntryJList;
    private DefaultListModel<AddressEntry> myaddressEntryListModel = new DefaultListModel<AddressEntry>();
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
            book = dataBaseConnect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        addressEntryList = book.list(); //add entries to addressEntry

        for(int i = 0; i<addressEntryList.size(); i++)
        {  this.myaddressEntryListModel.add(i, this.addressEntryList.get(i)); }

        //Now when we create our JList do it from our ListModel rather than our vector of AddressEntry
        addressEntryJList = new JList<AddressEntry>(this.myaddressEntryListModel);

        // create JList using the addressEntryList
        this.addressEntryJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.addressEntryJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        this.addressEntryJList.setVisibleRowCount(-1);

        frame = new JFrame();
        frame.setBounds(100, 100, 600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create scrollPane associated with JList
        scrollPane = new JScrollPane(this.addressEntryJList);
        //MainPanel.add(scrollPane);
        frame.getContentPane().add(searchPanel, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane,   BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        //frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // setup for the top panel 'searchPanel'
        textField1 = new JTextField();
        textField1.setColumns(15);              // gives text field enough room for user to input last name
        searchLabel = new JLabel("Search");
        enterButton = new JButton("Enter");
        searchPanel.add(searchLabel);
        searchPanel.add(textField1);
        searchPanel.add(enterButton);

        // initilizing buttons
        newButton = new JButton("Add");
        removeButton = new JButton("Remove");
        updateButton = new JButton("Update");
        exitButton = new JButton("Exit");
        // addding buttons
        buttonPanel.add(newButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(exitButton);
        /**
         * Removes an entry from list and database
         */
        removeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0) {
                int index = addressEntryJList.getSelectedIndex();
                //must delete from the database
                try {
                    String name = addressEntryJList.getModel().getElementAt(index).getName().getLastName(); // get the entry we seek to delete
                    removeDataBaseInfo(name);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                // delete from JList
                if(index != -1)//something is selected otherwise do nothing
                {
                    //retrieve the DeffaultListModel associated with our JList and remove from it the AddressEntry at this index
                    ((DefaultListModel<AddressEntry>) (addressEntryJList.getModel())).remove(index);
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

        //check Oracle documentation online
        // Or could do DriverManager.registerDriver (new oracle.jdbc.OracleDriver());

        // Connect to the database
        // generic host url = jdbc:oracle:thin:login/password@host:port/SID for Oracle SEE Account INFO you
        // were given by our CS tech in an email ---THIS WILL BE DIFFERENT
        //jdbc:oracle:thin:@//adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu
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
                System.out.println(val);
                System.out.println("======");
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
                //System.out.print(rset.getString(i) + " | ");
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

    public static void removeDataBaseInfo(String name) throws SQLException, ClassNotFoundException
    {
        // Load the Oracle JDBC driver
        Class.forName ("oracle.jdbc.OracleDriver"); //name of driver may change w/ versions

        //check Oracle documentation online
        // Or could do DriverManager.registerDriver (new oracle.jdbc.OracleDriver());

        // Connect to the database
        // generic host url = jdbc:oracle:thin:login/password@host:port/SID for Oracle SEE Account INFO you
        // were given by our CS tech in an email ---THIS WILL BE DIFFERENT
        //jdbc:oracle:thin:@//adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu
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
                System.out.println(val);
                System.out.println("======");
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
                //System.out.print(rset.getString(i) + " | ");
            }
            int newZip = Integer.parseInt(zip);
            AddressEntry newEntry = new AddressEntry(firstname, lastName, street, city, state, newZip,email, phone,id);
            int new_id = Integer.parseInt(newEntry.getID());
            if(name.equals(newEntry.getName().getLastName()))
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
