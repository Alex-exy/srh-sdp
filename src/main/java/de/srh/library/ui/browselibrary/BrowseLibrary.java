package de.srh.library.ui.browselibrary;


import de.srh.library.ui.createnewuser.CreateNewUser;
import de.srh.library.ui.mainmenu.MainMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseLibrary extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(CreateNewUser.class);
    private JPanel browseLibrary;
    private JLabel pageTitle;
    private JButton buttonSearch;
    private JTextField enterISBN;
    private JLabel labelISBN;
    private JTextField bookIDTextField;
    private JTextField bookTitleTextField;
    private JTextField bookAuthorTextField;
    private JTextField bookGenreTextField;
    private JComboBox searchLibrary;
    private JList resultsList;
    private JLabel labelResults;
    private JTextField doiTextField;
    private JTable resultsTableDisplay;
    private JButton goBack;

    public BrowseLibrary() {


        setAutoRequestFocus(false);
        setContentPane(browseLibrary);
        setTitle("Browse Library");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        logger.info("Opening create browse library window ...");

        JTable resultsTableDisplay = getjTable();
        JScrollPane scrollPane = new JScrollPane(resultsTableDisplay);


        //TEST DATA REPLACE WITH DATABASE
        // get database libraries forEach -> searchLibrary.addItem() . . .
        searchLibrary.addItem("Search all Libraries");
        searchLibrary.addItem("Heidelberg University Library");
        searchLibrary.addItem("Stadtbücherei Heidelberg");
        searchLibrary.addItem("Hauptbibliothek Heidelberg");


        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true);
            }
        });
    }

    private static JTable getjTable() {
        String[] columnNames = {"Book ID", "Title", "Subtitle", "Language", "ISBN", "Date Published", "Author", "Genre", "Description", "In Library/Library ID", "DOI", "Version"};

        //test data, delete this!
        Object[][] data = {};
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        return new JTable(tableModel);
    }

    public static void main(String[] args) {
        BrowseLibrary browseLibrary = new BrowseLibrary();
    }
}
