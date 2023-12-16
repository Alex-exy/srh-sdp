package de.srh.library.ui.addnewbook;


import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.ApiResponseCode;
import de.srh.library.entity.Book;
import de.srh.library.service.book.BookService;
import de.srh.library.service.book.BookServiceImpl;
import de.srh.library.ui.login.LoginWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class AddNewBook extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel addNewBookWindow;
    private JButton cancelButton;
    private JLabel pageTitle;
    private JButton saveAndAddBookButton;
    private JTextField doiField;
    private JTextField libraryIDField;
    private JTextField descriptionField;
    private JTextField priceField;
    private JTextField genreField;
    private JTextField authorField;
    private JTextField publishDateField;
    private JTextField isbnField;
    private JTextField languageField;
    private JTextField subtitleField;
    private JTextField titleField;
    private JComboBox genreDropDown;
    private JComboBox libraryDropDown;

    private BookService bookService;
    private Map<String, Integer> genresMap;
    private Map<String,Integer> libraryMap;

    private Book book;

    public AddNewBook() {

        setAutoRequestFocus(false);
        bookService = BookServiceImpl.createInstance();
        getGenres();
        getLibraries();

        setContentPane(addNewBookWindow);
        setTitle("Add New Book");
        setSize(720, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        logger.info("Opening add new book window ...");


        saveAndAddBookButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


              bookService = BookServiceImpl.createInstance();
                book = new Book();
                book.setBookName(titleField.getText());
                book.setSubtitles(subtitleField.getText());
                book.setLanguage(languageField.getText());
                book.setIsbn(isbnField.getText());
                book.setPublishDate(publishDateField.getText());
                book.setBookAuthor(authorField.getText());
                book.setGenreId(genresMap.get(genreDropDown.getSelectedItem()));
                book.setPrice(priceField.getText());
                book.setBookDescription(descriptionField.getText());
                book.setLibraryId(libraryMap.get(libraryDropDown.getSelectedItem()));
                book.setDoi(doiField.getText());
                bookService.insertBook(book);
                //Check valid inputs
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    public void getGenres(){
        ApiResponse<Map<String, Integer>> apiResponseGenre = bookService.getAllGenres();

        if (ApiResponseCode.SUCCESS.getCode() == apiResponseGenre.getCode()){
            genresMap = apiResponseGenre.getData();
            genresMap.forEach((s, i) -> genreDropDown.addItem(s));
        }
    }
    public void getLibraries(){
        ApiResponse<Map<String, Integer>> apiResponseLibrary = bookService.getAllLibraries();
        if (ApiResponseCode.SUCCESS.getCode() == apiResponseLibrary.getCode()){
            libraryMap = apiResponseLibrary.getData();
            libraryMap.forEach((s, i) -> libraryDropDown.addItem(s));
        }
    }

    public static void main(String[] args){
        new AddNewBook();
    }

}
