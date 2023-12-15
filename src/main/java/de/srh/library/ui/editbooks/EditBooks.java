package de.srh.library.ui.editbooks;


import de.srh.library.dto.ApiResponse;
import de.srh.library.service.book.BookService;
import de.srh.library.service.book.BookServiceImpl;
import de.srh.library.ui.addnewbook.AddNewBook;
import de.srh.library.ui.editbookdata.EditBookData;
import de.srh.library.ui.login.LoginWindow;
import de.srh.library.ui.managementmenu.ManagementMenu;
import de.srh.library.ui.removebook.RemoveBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBooks extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel editBooksWindow;
    private JButton goBack;
    private JLabel pageTitle;
    private JButton addNewBookButton;
    private JButton removeBookButton;
    private JButton editBookDataButton;
    private JTextField bookIDField;
    private JButton searchBookButton;
    private BookService bookService;


    public EditBooks() {

        setAutoRequestFocus(false);
        setContentPane(editBooksWindow);
        setTitle("Edit Book Menu");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        logger.info("Opening edit books window ...");

        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ManagementMenu managementMenu = new ManagementMenu();
                managementMenu.setVisible(true);
            }
        });
        addNewBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewBook addNewBook = new AddNewBook();
                addNewBook.setVisible(true);
            }
        });

        searchBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long bookId = Long.parseLong(bookIDField.getText());
                getBookById(bookId);

                if (bookFound()) {
                    bookService = BookServiceImpl.createInstance();
                    JOptionPane.showMessageDialog(null, " Book found!" + bookService.getBookById(bookId));
                } else {
                    JOptionPane.showMessageDialog(null, "Book does not exist! \nPlease try again!");
                }
            }
        });

        /* BUTTON NO LONGER EXISTENT
        editBookDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookService = BookServiceImpl.createInstance();
                long bookId = Long.parseLong(bookIDField.getText());
                if(bookFound()){
                    EditBookData editBookData = new EditBookData(bookId);
                    editBookData.setVisible(true);
                }
                else{

                    JOptionPane.showMessageDialog(null, "Book does not exist! \nPlease try again!");
                }
            }
        });
        */

    }

    private ApiResponse getBookById(long bookId) {
        bookService = BookServiceImpl.createInstance();
        return bookService.getBookById((bookId));
    }

    public boolean bookFound() {
        return bookService.bookFound(Long.parseLong(bookIDField.getText())).getData() == 1;
    }


    public static void main(String[] args) {
        EditBooks editBooks = new EditBooks();
    }

}
