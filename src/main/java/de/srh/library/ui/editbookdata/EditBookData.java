package de.srh.library.ui.editbookdata;


import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.ApiResponseCode;
import de.srh.library.dto.BookDto;
import de.srh.library.service.book.BookService;
import de.srh.library.service.book.BookServiceImpl;
import de.srh.library.ui.login.LoginWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Map;

public class EditBookData extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel editBookDataWindow;
    private JButton cancelButton;
    private JLabel pageTitle;
    private Date date = new Date();
    private JButton saveChangesButton;
    private JTextField doiField;
    private JTextField libraryIDField;
    private JTextField updateDateField;
    private JTextField addDateField;
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
    private JButton editDataButton;
    private JButton deleteBookButton;

    private BookService bookService;
    private Map<String, Integer> genresMap;
    private Map<String,Integer> libraryMap;


    public EditBookData(long bookId) {

        setAutoRequestFocus(false);
        bookService = BookServiceImpl.createInstance();


        setContentPane(editBookDataWindow);
        setTitle("Edit Book Data");
        setSize(720, 1000);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        logger.info("Opening edit book data window ...");
        loadCurrentBookData(bookId);



        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Changes Saved!");
                updateBookData(bookId);
                saveChangesButton.setEnabled(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        editDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChangesButton.setEnabled(true);
            }
        });
        deleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookService = BookServiceImpl.createInstance();
                bookService.removeBook(bookId);
            }
        });
    }

    public void loadCurrentBookData(long bookId) {

        bookService = BookServiceImpl.createInstance();
        getLibraries();
        getGenres();
        BookDto bookDto = bookService.getBookById(bookId).getData();
        titleField.setText(bookDto.getBookName());
        subtitleField.setText(bookDto.getSubtitles());
        languageField.setText(bookDto.getLanguage());
        isbnField.setText(bookDto.getIsbn());
        genreDropDown.setSelectedItem(bookGenreName(bookId));
        libraryDropDown.setSelectedItem(bookLibraryName(bookId));
        publishDateField.setText(bookDto.getPublishDate());
        authorField.setText(bookDto.getBookAuthor());
        priceField.setText(bookDto.getPrice());
        descriptionField.setText(bookDto.getBookDescription());
        doiField.setText(bookDto.getDoi());
    }

    private ApiResponse updateBookData(long bookId) {
        BookDto bookDto = new BookDto();
        bookDto.setBookId(bookId);
        bookDto.setBookName(titleField.getText());
        bookDto.setSubtitles(subtitleField.getText());
        bookDto.setLanguage(languageField.getText());
        bookDto.setIsbn(isbnField.getText());
        bookDto.setPublishDate(publishDateField.getText());
        bookDto.setBookAuthor(authorField.getText());
        bookDto.setGenreId(genresMap.get(genreDropDown.getSelectedItem().toString()));
        bookDto.setPrice(priceField.getText());
        bookDto.setBookDescription(descriptionField.getText());
        bookDto.setLibraryId(libraryMap.get(libraryDropDown.getSelectedItem().toString()));
        bookDto.setUpdateDate(new Date());
        bookDto.setDoi(doiField.getText());
        return bookService.updateBookInfo(bookDto);
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
    private String bookGenreName(long bookId){
        bookService = BookServiceImpl.createInstance();
        BookDto bookDto = new BookDto();
        return bookDto.getGenreName(bookId);
    }
    private String bookLibraryName(long bookId){
        bookService = BookServiceImpl.createInstance();
        BookDto bookDto = new BookDto();
        return bookDto.getLibraryName(bookId);
    }

    public static void main(String[] args) {
        EditBookData editBookData = new EditBookData(1L);
    }

}
