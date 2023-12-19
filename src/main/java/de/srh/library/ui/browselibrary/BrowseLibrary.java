package de.srh.library.ui.browselibrary;


import cn.hutool.core.exceptions.ValidateException;
import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.ApiResponseCode;
import de.srh.library.dto.BookDto;
import de.srh.library.service.book.BookService;
import de.srh.library.service.book.BookServiceImpl;
import de.srh.library.ui.createnewuser.CreateNewUser;
import de.srh.library.ui.mainmenu.MainMenu;
import de.srh.library.util.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


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
    private JLabel labelResults;
    private JTextField doiTextField;
    private JTable resultsTableDisplay;
    private JButton goBack;
    private JComboBox searchGenre;
    private JScrollPane resultScrollPane;
    private JPanel resultPanel;
    private JButton nextButton;
    private BookService bookService;
    private Map<String, Integer> genresMap;
    private Map<String,Integer> libraryMap;

    private Map<Long,List<String>> findBooks;
    private int currentPage = 1;
    private int itemsPerPage = 10;
    public BrowseLibrary() {

        bookService = BookServiceImpl.createInstance();
        getLibraries();
        getGenres();
        resultsTableDisplay.setModel(getjTableDefault());



        searchGenre.insertItemAt("None",0);
        searchLibrary.insertItemAt("None",0);
        searchGenre.setSelectedItem("None");
        searchLibrary.setSelectedItem("None");
        setAutoRequestFocus(false);
        setContentPane(browseLibrary);
        setTitle("Browse Library");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        logger.info("Opening create browse library window ...");



        buttonSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                boolean bookTitleText = bookTitleTextField.getText().trim().isEmpty(),
                        bookAuthorText = bookAuthorTextField.getText().trim().isEmpty(),
                        isbnText = enterISBN.getText().trim().isEmpty(),
                        doiText = doiTextField.getText().trim().isEmpty(),
                        bookIdText = bookIDTextField.getText().trim().isEmpty(),
                        bookGenre = Objects.equals(searchGenre.getSelectedItem(), "None"),
                        bookLibrary = Objects.equals(searchLibrary.getSelectedItem(), "None");

                
                if(bookTitleText && bookAuthorText && isbnText && doiText && bookIdText && bookGenre && bookLibrary){
                    JOptionPane.showMessageDialog(null, "Please enter or select at least 1 search parameter");
                    resultsTableDisplay.setModel(getjTableDefault());
                } else  {
                    if(bookFoundAndValidation()) {
                        resultsTableDisplay.setModel(findBooksJTable());
                    }else{
                        resultsTableDisplay.setModel(getjTableDefault());
                    }
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

    private  TableModel findBooksJTable() {
        DefaultTableModel tableModel = createTableModel();


        bookService = BookServiceImpl.createInstance();
        ApiResponse<Map<Long,List<String>>> apiResponseFind = bookService.findBooks(
                (bookTitleTextField.getText().trim().isEmpty() ? null : bookTitleTextField.getText().trim()),
                (bookAuthorTextField.getText().trim().isEmpty() ? null : bookAuthorTextField.getText().trim()),
                genresMap.get(Objects.requireNonNull(searchGenre.getSelectedItem()).toString()) == null ?  0: genresMap.get(searchGenre.getSelectedItem().toString()),
                (enterISBN.getText().trim().isEmpty() ? null : enterISBN.getText().trim()),
                (doiTextField.getText().trim().isEmpty() ? null : doiTextField.getText().trim()),
                (parseBookID(bookIDTextField.getText().trim())),
                libraryMap.get(Objects.requireNonNull(searchLibrary.getSelectedItem()).toString()) == null ? 0: libraryMap.get(searchLibrary.getSelectedItem().toString()));

        if(ApiResponseCode.SUCCESS.getCode() == apiResponseFind.getCode()){
            findBooks = apiResponseFind.getData();
            for(Map.Entry<Long,List<String>> entry : findBooks.entrySet()) {
                List<String> values = entry.getValue();
                Object[] rowData = new Object[7];
                rowData[0] = entry.getKey();
                for (int i = 0; i < values.size(); i++) {
                    rowData[i + 1] = values.get(i);
                }
                tableModel.addRow(rowData);
            }
        }
        return tableModel;

    }

    private  TableModel getjTableDefault() {
        String[] columnNames = {"Book ID", "Title","Book Author","Genre","ISBN","DOI","In Library"};
        Object[][] data ={
                {"No Record Found","No Record Found","No Record Found","No Record Found","No Record Found"
                        ,"No Record Found","No Record Found"}
        };
        return new DefaultTableModel(data, columnNames);
    }
    private DefaultTableModel createTableModel() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Book ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Book Author");
        tableModel.addColumn("Genre");
        tableModel.addColumn("ISBN");
        tableModel.addColumn("DOI");
        tableModel.addColumn("In Library");
        return tableModel;
    }

    private void getGenres(){
        ApiResponse<Map<String, Integer>> apiResponseGenre = bookService.getAllGenres();

        if (ApiResponseCode.SUCCESS.getCode() == apiResponseGenre.getCode()) {
            genresMap = apiResponseGenre.getData();

            TreeMap<String, Integer> sortedGenresMap = new TreeMap<>(genresMap);
            searchGenre.removeAllItems();
            sortedGenresMap.forEach((s, i) -> searchGenre.addItem(s));
        }
    }
    private void getLibraries(){
        ApiResponse<Map<String, Integer>> apiResponseLibrary = bookService.getAllLibraries();
        if (ApiResponseCode.SUCCESS.getCode() == apiResponseLibrary.getCode()) {
            libraryMap = apiResponseLibrary.getData();

            TreeMap<String, Integer> sortedLibrariesMap = new TreeMap<>(libraryMap);
            searchLibrary.removeAllItems();
            sortedLibrariesMap.forEach((s, i) -> searchLibrary.addItem(s));
        }
    }
    public boolean bookFoundAndValidation() {
        BookDto bookDto = new BookDto();
        String bookTitle = bookTitleTextField.getText().trim();
        String bookId = bookIDTextField.getText().trim();
        String isbn = enterISBN.getText().trim();
        String doi = doiTextField.getText().trim();
        String bookAuthor =  bookAuthorTextField.getText().trim();

        if (isbn.isEmpty()) {
            bookDto.setIsbn(null);
        } else {
            try {
                ValidatorUtils.validateIsbn(isbn);
                bookDto.setIsbn(isbn);
            } catch (ValidateException ve) {
                JOptionPane.showMessageDialog(null, ve.getMessage());
            }
        }
        if (doi.isEmpty()) {
            bookDto.setDoi(null);
        } else {
            try {
                ValidatorUtils.validateDoi(doi);
                bookDto.setDoi(doi);
            } catch (ValidateException ve) {
                JOptionPane.showMessageDialog(null, ve.getMessage());
            }
        }
        if (bookAuthor.isEmpty()) {
            bookDto.setBookAuthor(null);
        } else {
            try {
                ValidatorUtils.validateAuthor(bookAuthor);
                bookDto.setBookAuthor(bookAuthor);
            } catch (ValidateException ve) {
                JOptionPane.showMessageDialog(null, ve.getMessage());
            }
        }
        bookDto.setBookId(parseBookID(bookId));
        bookDto.setBookName(bookTitle.isEmpty() ? null : (bookTitle));
        bookDto.setGenreId(
                genresMap.get(searchGenre.getSelectedItem()) == null ?  0: genresMap.get(searchGenre.getSelectedItem())
        );
        bookDto.getLibraryName(libraryMap.get(
                libraryMap.get(searchLibrary.getSelectedItem())) == null ? 0: libraryMap.get(searchLibrary.getSelectedItem())
        );
        bookService = BookServiceImpl.createInstance();
        bookService.bookFound(bookDto);
        return bookService.bookFound(bookDto).getData() != 0;
    }
    public long parseBookID(String input) {
        try {
            return input.isEmpty() ? 0L : Long.parseLong(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input for book ID. Please enter a valid number.");
            throw e;
        }
    }


    public static void main(String[] args) {
        BrowseLibrary browseLibrary = new BrowseLibrary();
    }
}
