package de.srh.library.ui.editbooks;

import de.srh.library.dao.BookDao;
import de.srh.library.entity.Book;
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

    private BookDao bookDao = new BookDao();



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
        removeBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveBook removeBook = new RemoveBook();
                removeBook.setVisible(true);
            }
        });

        editBookDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        searchBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long bookId = Long.parseLong(bookIDField.getText());
                getBookById(bookId);

                    if (bookFound()) {
                        JOptionPane.showMessageDialog(null, " Book found!" + bookDao.getBookById(bookId));
                    }else {
                            JOptionPane.showMessageDialog(null, "Book does not exist! \nPlease try again!");
                        }


            }
        });

        editBookDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(bookFound()){
                    EditBookData editBookData = new EditBookData(Long.parseLong(bookIDField.getText()));
                    editBookData.setVisible(true);
                }
                else{

                    JOptionPane.showMessageDialog(null, "Book does not exist! \nPlease try again!");
                }
            }
        });

    }
    public Book getBookById(Long bookId){
        return bookDao.getBookById((bookId));
    }

    public boolean bookFound(){
        return bookDao.bookFound(Long.parseLong(bookIDField.getText())) == 1;
    }



    public static void main(String[] args) {
        EditBooks editBooks = new EditBooks();
    }

}
