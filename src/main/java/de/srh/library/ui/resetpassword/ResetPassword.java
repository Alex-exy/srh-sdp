package de.srh.library.ui.resetpassword;

import de.srh.library.ui.login.LoginWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;

public class ResetPassword extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel resetPasswordWindow;
    private JLabel pageTitle;
    private JPasswordField newPassword1;
    private JPasswordField newPassword2;
    private JButton saveButton;
    private JTextField emailCheck;
    private JLabel description;
    private JButton cancelButton;

    //Testing only
    private String email = "Valid Email";

    public ResetPassword() {

        setAutoRequestFocus(false);
        setContentPane(resetPasswordWindow);
        setTitle("Reset Password");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        logger.info("Requesting password reset ...");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkEnteredCredentials();
            }
        });

        //Replace with email address check from database!
        emailCheck.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                saveButton.setEnabled(true);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public boolean checkValidEmail(String email) {
        //Check for valid Email with database credentials
        return true;
    }

    public void checkEnteredCredentials() {
        System.out.println(newPassword1.getPassword());
        System.out.println(newPassword2.getPassword());

        if (Arrays.equals(newPassword1.getPassword(), newPassword2.getPassword()) && checkValidEmail(email)) {
            JOptionPane.showMessageDialog(null, "Password successfully reset!");
            saveButton.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Passwords do not match or Email not registered!");
        }
    }

    // Testing only
    public static void main(String[] args) {
        ResetPassword resetPassword = new ResetPassword();
    }

}
