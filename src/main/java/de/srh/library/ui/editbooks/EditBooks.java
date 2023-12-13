package de.srh.library.ui.editbooks;

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
        searchBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                //Check book id with database if book is existing
                JOptionPane.showMessageDialog(null, "Book found!");
                JOptionPane.showMessageDialog(null, "Book nonexistent! \nPlease try again!");
            }
        });
        editBookDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditBookData editBookData = new EditBookData(bookIDField.toString());
                editBookData.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        EditBooks editBooks = new EditBooks();
    }

}
