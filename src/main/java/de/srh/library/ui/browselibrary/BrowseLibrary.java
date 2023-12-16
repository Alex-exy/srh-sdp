package de.srh.library.ui.browselibrary;


import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.ApiResponseCode;
import de.srh.library.service.book.BookService;
import de.srh.library.service.book.BookServiceImpl;
import de.srh.library.ui.createnewuser.CreateNewUser;
import de.srh.library.ui.mainmenu.MainMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

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
    private JComboBox searchLibrary;
    private JList resultsList;
    private JLabel labelResults;
    private JTextField doiTextField;
    private JTable resultsTableDisplay;
    private JButton goBack;
    private JComboBox searchGenre;
    private BookService bookService;
    private Map<String, Integer> genresMap;
    private Map<String,Integer> libraryMap;

    public BrowseLibrary() {

        bookService = BookServiceImpl.createInstance();
        getLibraries();
        getGenres();
        searchGenre.addItem("None");
        searchGenre.setSelectedItem("None");
        searchLibrary.addItem("None");
        searchLibrary.setSelectedItem("None");
        setAutoRequestFocus(false);
        setContentPane(browseLibrary);
        setTitle("Browse Library");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        logger.info("Opening create browse library window ...");

        JTable resultsTableDisplay = getjTable();
        JScrollPane scrollPane = new JScrollPane(resultsTableDisplay);





        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bookTitleTextField.getText().trim().isEmpty() && bookAuthorTextField.getText().trim().isEmpty()
                        && enterISBN.getText().trim().isEmpty() && doiTextField.getText().trim().isEmpty()
                        && bookIDTextField.getText().trim().isEmpty() && searchGenre.getSelectedItem().equals("None")
                        && searchLibrary.getSelectedItem().equals("None")){
                    JOptionPane.showMessageDialog(null, "Please enter or select atleast 1 search parameter");
                    System.out.println();
                }
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
    public void getLibraries(){
        ApiResponse<Map<String, Integer>> apiResponseLibrary = bookService.getAllLibraries();
        if (ApiResponseCode.SUCCESS.getCode() == apiResponseLibrary.getCode()){
            libraryMap = apiResponseLibrary.getData();
            libraryMap.forEach((s, i) -> searchLibrary.addItem(s));
        }
    }
    public void getGenres(){
        ApiResponse<Map<String, Integer>> apiResponseGenre = bookService.getAllGenres();
        if (ApiResponseCode.SUCCESS.getCode() == apiResponseGenre.getCode()){
            genresMap = apiResponseGenre.getData();
            genresMap.forEach((s, i) -> searchGenre.addItem(s));
        }
    }

    public static void main(String[] args) {
        BrowseLibrary browseLibrary = new BrowseLibrary();
    }
}
