package de.srh.library.ui.editbookdata;

import de.srh.library.dao.BookDao;
import de.srh.library.entity.Genre;
import de.srh.library.ui.editbooks.EditBooks;
import de.srh.library.ui.login.LoginWindow;
import de.srh.library.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBookData extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel editBookDataWindow;
    private JButton cancelButton;
    private JLabel pageTitle;
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
    private BookDao bookDao = new BookDao();


    public EditBookData(long bookId) {

        setAutoRequestFocus(false);
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
                updateBookData(bookId);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void loadCurrentBookData(long bookId) {

        Book book = bookDao.getBookById(bookId);
        Genre genre = new Genre();

    titleField.setText(book.getBookName());
    subtitleField.setText(book.getSubtitles());
    languageField.setText(book.getLanguage());
    isbnField.setText(book.getIsbn());
    publishDateField.setText(book.getPublishDate());
    authorField.setText(book.getBookAuthor());
    genreField.setText(genre.getGenreName());
    priceField.setText(book.getPrice());
    descriptionField.setText(book.getBookDescription());
    addDateField.setText(book.getAdditionDate().toString());
    updateDateField.setText(book.getUpdateDate().toString());
    libraryIDField.setText(Integer.toString(book.getLibraryId()));
    doiField.setText(book.getDoi());



    }
    public void updateBookData(Long bookId) {
        Book book = new Book();
        book.setBookId(bookId);
        book.setBookName(titleField.getText());
        book.setSubtitles(subtitleField.getText());
        book.setLanguage(languageField.getText());
        book.setIsbn(isbnField.getText());
        book.setPublishDate(publishDateField.getText());
        book.setBookAuthor(authorField.getText());
        book.setPrice(priceField.getText());
        book.setBookDescription(descriptionField.getText());
        book.setLibraryId(Integer.parseInt(libraryIDField.getText()));
        book.setDoi(doiField.getText());
        bookDao.updateBookInfo(book);
    }

    public static void main(String[] args) {
        EditBookData editBookData = new EditBookData(4L);
    }

}
