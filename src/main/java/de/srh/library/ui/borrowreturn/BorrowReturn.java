package de.srh.library.ui.borrowreturn;

import de.srh.library.ui.login.LoginWindow;
import de.srh.library.ui.mainmenu.MainMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class BorrowReturn extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel borrowReturnWindow;
    private JLabel pageTitle;
    private JList borrowedBookList;
    private JTextField borrowBookID;
    private JButton borrowButton;
    private JComboBox borrowDurationBox;
    private JTextField returnBookID;
    private JComboBox selectBorrowedBook;
    private JButton returnButton;
    private JButton extendButton;
    private JButton goBack;

    private String enteredBookID;

    //Testing content - book list
    ArrayList<String> borrowedBooks = new ArrayList<String>(Arrays.asList("Harry Potter", "The Lord of the Rings", "A Song of Fire and Ice", "The  Hunger Games", "The Art of War"));

    public BorrowReturn() {

        setAutoRequestFocus(false);
        setContentPane(borrowReturnWindow);
        setTitle("Borrow and Return");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        logger.info("Opening borrow and return window ...");

        initializeBorrowedUserBooks();
        //Not necessary HERE if init func working
        updateBorrowedBookList();
        setDurationListContent();

        borrowedBookList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                returnBookID.setText(borrowedBookList.getSelectedValue().toString());
                System.out.println(borrowedBookList.getSelectedValue().toString());
                returnButton.setEnabled(true);
            }
        });
        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Database logic
                // Check if valid input
                // Check entered duration valid and safe book + duration in database

                if (borrowedBooks.contains(borrowBookID.getText().toString())) {
                    JOptionPane.showMessageDialog(null, "Book already borrowed!");
                } else {
                    enteredBookID = borrowBookID.getText().toString();
                    borrowedBooks.add(enteredBookID);
                    updateBorrowedBookList();
                }
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Database logic + remove from borrowed list
                removeSelectedBook(returnBookID.getText().toString());
                updateBorrowedBookList();
            }
        });
        extendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Database Logic + update book borrow data
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

    public void updateBorrowedBookList() {
        //Get database data from current borrowed books from the user
        borrowedBookList.setListData(borrowedBooks.toArray());
        System.out.println(borrowedBooks);
    }
    public void setDurationListContent() {
        for (String s : Arrays.asList("One Week", "Two Weeks", "Three Weeks")) {
            borrowDurationBox.addItem(s);
        }
    }
    public void removeSelectedBook(String selectedBook) {
        System.out.println(selectedBook);
        if (borrowedBooks.contains(selectedBook)) {
            borrowedBooks.remove(selectedBook);
        } else {
            JOptionPane.showMessageDialog(null, "Book not found!");
        }
    }
    public void initializeBorrowedUserBooks() {
        //Database logic get books borrowed by user and fill displayed list
        //Print testing borrowed books
        System.out.println(borrowedBooks);
    }
    public void confirmBorrowRequest() {
        //Confirmation for borrowing book request
    }
    public static void main(String[] args) {
        BorrowReturn borrowReturn = new BorrowReturn();
    }
}
