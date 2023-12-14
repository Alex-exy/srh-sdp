package de.srh.library.ui.addnewbook;

import de.srh.library.dao.BookDao;
import de.srh.library.entity.Book;
import de.srh.library.mapper.books.BookMapper;
import de.srh.library.ui.editbooks.EditBooks;
import de.srh.library.ui.login.LoginWindow;
import de.srh.library.ui.managementmenu.ManagementMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class AddNewBook extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel addNewBookWindow;
    private JButton cancelButton;
    private JLabel pageTitle;
    private JButton saveAndAddBookButton;
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

    private Book book;

    public AddNewBook() {

        setAutoRequestFocus(false);
        setContentPane(addNewBookWindow);
        setTitle("Add New Book");
        setSize(720, 1000);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        logger.info("Opening add new book window ...");


        saveAndAddBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookDao bookDao = new BookDao();
                book = new Book();
                book.setBookName(titleField.getText());
                book.setSubtitles(subtitleField.getText());
                book.setLanguage(languageField.getText());
                book.setIsbn(isbnField.getText());
                book.setPublishDate(publishDateField.getText());
                book.setBookAuthor( authorField.getText());
                book.setGenreId(Integer.parseInt(genreField.getText()));
                book.setPrice(priceField.getText());
                book.setBookDescription(descriptionField.getText());
                book.setLibraryId(Integer.parseInt(libraryIDField.getText()));
                book.setDoi(doiField.getText());
                bookDao.insertBook(book);
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

    public static void main(String[] args){
        new AddNewBook();
    }

}
