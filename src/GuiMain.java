import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class GuiMain
{
    private JFrame frame;
    private JScrollPane scrollPane;
    // The Panels
    private JPanel MainPanel = new JPanel();
    private JPanel listPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    // The five buttons
    private JButton newButton;
    private JButton removeButton;
    private JButton updateButton;
    private JButton displayButton;
    private JButton exitButton;

    private Vector<AddressEntry> addressEntryList = new Vector<AddressEntry>();
    private JList <AddressEntry> addressEntryJList;
    private DefaultListModel<AddressEntry> myaddressEntryListModel = new DefaultListModel<AddressEntry>();

    public GuiMain()
    {
        addressEntryList.add(new AddressEntry("Lynne", "Grewe", "33 A street", "Hayward", "CA", 9399,"l@csueastbay.edu","555-1212", "41221"));
        addressEntryList.add(new AddressEntry("Jane", "Doe", "22 Cobble street", "Hayward", "CA", 9399,"jane@csueastbay.edu","555-9999", "q342"));
        addressEntryList.add(new AddressEntry("Lynne", "Grewe", "33 A street", "Hayward", "CA", 9399,"l@csueastbay.edu","555-1212", "41221"));
        addressEntryList.add(new AddressEntry("Lynne", "Grewe", "33 A street", "Hayward", "CA", 9399,"l@csueastbay.edu","555-1212", "41221"));
        addressEntryList.add(new AddressEntry("Lynne", "Grewe", "33 A street", "Hayward", "CA", 9399,"l@csueastbay.edu","555-1212", "41221"));
        addressEntryList.add(new AddressEntry("Lynne", "Grewe", "33 A street", "Hayward", "CA", 9399,"l@csueastbay.edu","555-1212", "41221"));
        addressEntryList.add(new AddressEntry("Lynne", "Grewe", "33 A street", "Hayward", "CA", 9399,"l@csueastbay.edu","555-1212", "41221"));

        for(int i = 0; i<addressEntryList.size(); i++)
        {  this.myaddressEntryListModel.add(i, this.addressEntryList.elementAt(i)); }

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
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        //frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);



        newButton = new JButton("Add");
        removeButton = new JButton("Remove");
        updateButton = new JButton("Update");
        displayButton = new JButton("Display");
        exitButton = new JButton("Exit");

        buttonPanel.add(newButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(displayButton);
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
}
