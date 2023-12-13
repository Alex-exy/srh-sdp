package de.srh.library.ui.removebook;

import de.srh.library.service.user.UserServiceImpl;
import de.srh.library.ui.resetpassword.ResetPassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveBook extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(ResetPassword.class);
    private JPanel removeBookWindow;
    private JButton returnButton;
    private JLabel pageTitle;
    private JLabel description;
    private JTextField emailCheck;
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
                //Logic remove book with entered book id from database
                //Check entered book id valid
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    public static void main(String[] args) {
        new RemoveBook();
    }
}
