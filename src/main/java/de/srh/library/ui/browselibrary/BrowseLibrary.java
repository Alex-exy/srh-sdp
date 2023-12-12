package de.srh.library.ui.browselibrary;


import de.srh.library.ui.createnewuser.CreateNewUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class BrowseLibrary extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(CreateNewUser.class);
    private JPanel browseLibrary;
    private JLabel pageTitle;
    private JButton buttonSearch;
    private JTextField enterISBN;
    private JLabel lableISBN;
    private JTextField bookIDTextField;
    private JTextField bookTitleTextField;
    private JTextField bookAuthorTextField;
    private JTextField bookGenreTextField;
    private JComboBox searchLibrary;
    private JList resultsList;
    private JLabel lableResults;
    private JTextField DOITextField;

    public BrowseLibrary() {

        searchLibrary.addItem("Search all Libraries");
        searchLibrary.addItem("Heidelberg University Library");
        searchLibrary.addItem("Stadtbücherei Heidelberg");
        searchLibrary.addItem("Hauptbibliothek Heidelberg");

        setAutoRequestFocus(false);
        setContentPane(browseLibrary);
        setTitle("Browse Library");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        logger.info("Opening create browse library window ...");

    }
    public static void main(String[] args) {BrowseLibrary browseLibrary = new BrowseLibrary();}

}
