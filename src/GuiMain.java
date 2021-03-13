import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

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
    public GuiMain()
    {
        boolean wellsee = false;
        singleWindow = false;
        book.add(new AddressEntry("Nick", "Grewe", "33 A street", "Hayward", "CA", 9399,"l@csueastbay.edu","555-1212", "41221"));
        book.add(new AddressEntry("Jane", "Doe", "22 Cobble street", "Hayward", "CA", 9399,"jane@csueastbay.edu","555-9999", "q342"));
        book.add(new AddressEntry("Lynne", "Bob", "33 A street", "Hayward", "CA", 9399,"l@csueastbay.edu","555-1212", "41221"));
        book.add(new AddressEntry("Sam", "Grewe", "33 A street", "Hayward", "CA", 9399,"l@csueastbay.edu","555-1212", "41221"));
        book.add(new AddressEntry("Pablo", "Grewe", "33 A street", "Hayward", "CA", 9399,"l@csueastbay.edu","555-1212", "41221"));
        book.add(new AddressEntry("Lynne", "Grewe", "33 A street", "Hayward", "CA", 9399,"l@csueastbay.edu","555-1212", "41221"));
        book.add(new AddressEntry("Lynne", "Grewe", "33 A street", "Hayward", "CA", 9399,"l@csueastbay.edu","555-1212", "41221"));

        addressEntryList = book.list();

        for(int i = 0; i<addressEntryList.size(); i++)
        {  this.myaddressEntryListModel.add(i, this.addressEntryList.get(i)); }

        //Now when we create our JList do it from our ListModel rather than our vector of AddressEntry
        addressEntryJList = new JList<AddressEntry>(this.myaddressEntryListModel);

        // create JList using the addressEntryList
        this.addressEntryJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.addressEntryJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        this.addressEntryJList.setVisibleRowCount(-1);

        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
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

        removeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0) {
                int index = addressEntryJList.getSelectedIndex();
                if(index != -1)//something is selected otherwise do nothing
                    //retrieve the DeffaultListModel associated with our JList and remove from it the AddressEntry at this index
                    ((DefaultListModel<AddressEntry>) (addressEntryJList.getModel())).remove(index);
            }

        });
        //scrollPane.setColumnHeaderView(btnRemove);
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
    }
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
