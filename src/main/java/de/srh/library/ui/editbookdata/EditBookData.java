package de.srh.library.ui.editbookdata;


import cn.hutool.core.exceptions.ValidateException;
import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.ApiResponseCode;
import de.srh.library.dto.BookDto;
import de.srh.library.service.book.BookService;
import de.srh.library.service.book.BookServiceImpl;
import de.srh.library.ui.ConfirmationRequest;
import de.srh.library.ui.login.LoginWindow;
import de.srh.library.util.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

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
        toFront();
        logger.info("Opening edit book data window ...");
        loadCurrentBookData(bookId);

        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    inputValidation();
                }catch (ValidateException ve ){
                    JOptionPane.showMessageDialog(null, ve.getMessage());
                    return;
                }

                ConfirmationRequest confirmation = new ConfirmationRequest();
                if(confirmation.userDecision) {
                    updateBookData(bookId);
                    saveChangesButton.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Changes saved!");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Changes discarded!");
                }
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
                ConfirmationRequest confirmation = new ConfirmationRequest();
                if(confirmation.userDecision) {
                    bookService = BookServiceImpl.createInstance();
                    bookService.removeBook(bookId);
                    JOptionPane.showMessageDialog(null, "Book deleted!");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Deletion canceled!");
                }
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
    private void getGenres(){
        ApiResponse<Map<String, Integer>> apiResponseGenre = bookService.getAllGenres();

        if (ApiResponseCode.SUCCESS.getCode() == apiResponseGenre.getCode()) {
            genresMap = apiResponseGenre.getData();

            TreeMap<String, Integer> sortedGenresMap = new TreeMap<>(genresMap);
            genreDropDown.removeAllItems();
            sortedGenresMap.forEach((s, i) -> genreDropDown.addItem(s));
        }
    }
    private void getLibraries(){
        ApiResponse<Map<String, Integer>> apiResponseLibrary = bookService.getAllLibraries();
        if (ApiResponseCode.SUCCESS.getCode() == apiResponseLibrary.getCode()) {
            libraryMap = apiResponseLibrary.getData();

            TreeMap<String, Integer> sortedLibrariesMap = new TreeMap<>(libraryMap);
            libraryDropDown.removeAllItems();
            sortedLibrariesMap.forEach((s, i) -> libraryDropDown.addItem(s));
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
    private void inputValidation(){
        ValidatorUtils.validateBookName(titleField.getText());
        ValidatorUtils.validateSubtitles(subtitleField.getText());
        ValidatorUtils.validateLanguage(languageField.getText());
        ValidatorUtils.validateIsbn(isbnField.getText());
        ValidatorUtils.validatePublishDate(publishDateField.getText());
        ValidatorUtils.validateAuthor(authorField.getText());
        ValidatorUtils.validateLanguage(languageField.getText());
        ValidatorUtils.validatePrice(priceField.getText());
        ValidatorUtils.validateBookDescription(descriptionField.getText());
        ValidatorUtils.validateDoi(doiField.getText());
    }

    public static void main(String[] args) {
        EditBookData editBookData = new EditBookData(10L);
    }

}
