import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;

public class SearchEntryList extends GuiMain {

    private JFrame frame;
    private JPanel mainPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JScrollPane theScrollPane;
    private JButton returnButton;

    private JList list1;
    private DefaultListModel<AddressEntry> myaddressEntryListModel = new DefaultListModel<AddressEntry>();
    private ArrayList<AddressEntry> addressEntryList = new ArrayList<AddressEntry>();

    SearchEntryList(String text)
    {
        frame = new JFrame();
        //Find the specific
        AddressBook newBook = getAddressBook();
        addressEntryList = newBook.find(text);

        for (int i = 0; i < addressEntryList.size(); i++) {
            this.myaddressEntryListModel.add(i, this.addressEntryList.get(i));
        }

        //Now when we create our JList do it from our ListModel rather than our vector of AddressEntry
        list1 = new JList<AddressEntry>(this.myaddressEntryListModel);

        // create JList using the addressEntryList
        this.list1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.list1.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        this.list1.setVisibleRowCount(-1);

        // initialize the scroll pane
        theScrollPane = new JScrollPane(this.list1);

        // set components on the frame
        frame.getContentPane().add(theScrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // initialize button and add to the 'buttonPanel'
        returnButton = new JButton("Return");
        buttonPanel.add(returnButton);

        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GuiMain obj = new GuiMain();
                boolean a = false;

                frame.setVisible(false);
            }
        });
    }

}
