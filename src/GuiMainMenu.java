import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class GuiMainMenu extends JFrame  {
    private JFrame frame = new JFrame("Address Book");
    private JButton newButton;
    private JButton removeButton;
    private JButton displayButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    boolean opened = false;

    private JPanel buttonPanel = new JPanel();
    private JPanel interfacePanel = new JPanel();
    private JPanel mainFrame = new JPanel();

    private DefaultListModel<AddressEntry> model = new DefaultListModel<AddressEntry>();    // allows you manage the list, ;defaultlistModel' allows us to delete items(mutable)
    private JList<AddressEntry> list1 = new JList<AddressEntry>(model);
    ;
    private JScrollPane theScrollPane;
    private JPanel inputInfo;
    private JTextField textField9;

    ArrayList<AddressEntry> addressEntryList = new ArrayList<>();
    AddressBook book = new AddressBook();

    public void openFile()
    {
        book.readFromFile("file.txt");
        book.sortList();
        addressEntryList = book.list();

    }
    public void sortList()
    {
        book.sortList();
        addressEntryList = book.list();
    }

    public GuiMainMenu() {

        list1.setSize(new Dimension(200,200));
        theScrollPane = new JScrollPane(list1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        buttonPanel.add(displayButton);
        buttonPanel.add(newButton);
        buttonPanel.add(removeButton);
        interfacePanel.add(theScrollPane);
        interfacePanel.add(inputInfo);
        //interfacePanel.add(topLabel);
        mainFrame.add(buttonPanel);
        mainFrame.add(interfacePanel);


        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                model.clear();
                if(opened == false) // opens file the first time you click display
                {
                    openFile();
                    opened = true;
                }
                // places all the entries from the ArrayList into the model
                for (int i = 0; i < addressEntryList.size(); i++)
                {
                    model.addElement(addressEntryList.get(i));
                }
                list1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                list1.setLayoutOrientation(JList.HORIZONTAL_WRAP);
                list1.setVisibleRowCount(-1);
            }
        });

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = textField1.getText();
                String b = textField2.getText();
                String c = textField3.getText();
                String d = textField4.getText();
                String ee = textField5.getText();
                String f =  textField6.getText();
                String g = textField7.getText();
                String h = textField8.getText();
                String i = textField9.getText();


                int ff =Integer.parseInt(f);
                Name name = new Name(a, b);
                Address address = new Address(c,d,ee,ff);
                AddressEntry newEntry = new AddressEntry(name, address, g, h, i);

                book.add(newEntry);
                sortList();
                addressEntryList = book.list(); // the ArrayList gets the new organized list

                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
                textField7.setText("");
                textField8.setText("");
                textField9.setText("");
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //model.removeElementAt(list1.getSelectedIndex());
                int index = list1.getSelectedIndex();
                if(index != -1)
                {
                    book.remove(addressEntryList.get(index).getName().getLastName(),addressEntryList.get(index).getName().getFirstName());
                    addressEntryList.remove(index);
                    model.removeElementAt(index);
                }
            }
        });


        frame.getContentPane().add(mainFrame);
        frame.setBounds(600, 600, 750, 750);
        frame.getContentPane().setLayout(new GridLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true); // sets frame visible
    }
    public static void main(String[] args)
    {
        GuiMainMenu mainMenu = new GuiMainMenu();
        mainMenu.frame.setVisible(true);
    }
}
