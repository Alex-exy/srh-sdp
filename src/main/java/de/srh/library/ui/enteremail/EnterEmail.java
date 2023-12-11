package de.srh.library.ui.enteremail;

import de.srh.library.ui.login.LoginWindow;
import de.srh.library.ui.resetpassword.ResetPassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class EnterEmail extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel enterEmailWindow;
    private JTextField textField1;
    private JButton resetPasswordButton;
    private JLabel pageTitle;
    private String test;

    public EnterEmail() {

        setAutoRequestFocus(false);
        setContentPane(enterEmailWindow);
        setTitle("Enter Email to reset Password");
        setSize(600, 400);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        logger.info("Enter Email for pw reset ...");
        resetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkValidEmail(test)) {
                    JOptionPane.showMessageDialog(null, "Email sent!");
                    resetPasswordButton.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Email not registered! \n Please try again!");
                    resetPasswordButton.setEnabled(true);
                }
            }
        });
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                resetPasswordButton.setEnabled(true);
            }
        });
    }

    public boolean checkValidEmail(String email) {
        //Check if email is existing in database
        /* PSEUDOCODE
        if (email == valid) {
            return true;
        }
        else {
            return false;
        } */
        return true;
    }
    public static void main(String[] args) {
        EnterEmail enterEmail = new EnterEmail();
    }
}
