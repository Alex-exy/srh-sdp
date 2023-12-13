package de.srh.library.ui.removebook;

import de.srh.library.dao.BookDao;
import de.srh.library.ui.resetpassword.ResetPassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.srh.library.entity.Book;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveBook extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(ResetPassword.class);
    private Book book;
    private JPanel removeBookWindow;
    private JButton returnButton;

    private JLabel pageTitle;
    private JLabel description;
    private JTextField bookId;
    private JButton removeBookFromDatabaseButton;

    public RemoveBook() {

        setAutoRequestFocus(false);
        setContentPane(removeBookWindow);
        setTitle("Remove Book");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        logger.info("Requesting password reset ...");

        removeBookFromDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeBook(Long.parseLong(bookId.getText()));
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    public void removeBook(Long bookId){
        BookDao bookDao = new BookDao();
        bookDao.removeBook(bookId);

    }
    public static void main(String[] args) {
        new RemoveBook();
    }
}
