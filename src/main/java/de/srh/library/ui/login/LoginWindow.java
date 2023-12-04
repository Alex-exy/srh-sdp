package de.srh.library.ui.login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;
import javax.swing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginWindow extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel LoginWindow;
    private JLabel Title;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JLabel LoginTitle;
    private JButton loginButton;
    private JButton createNewAccountButton;
    private JButton resetPasswordButton;
    private JButton FAQButton;

    public LoginWindow() throws HeadlessException {
        setContentPane(LoginWindow);
        setTitle("Please Login");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        logger.info("Starting login window ...");

        //Test User Data
        //Check with user data
        String testID = "asdf";
        String testPassword = "asdf";

        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Arrays.toString(passwordField.getPassword()).equals("password")) {
                    passwordField.setText("");
                }
            }
        });
        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("username")) {
                    usernameField.setText("");
                }
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("LoginButton Pressed");
                String username = usernameField.getText();
                System.out.println(username);
                char[] password = passwordField.getPassword();
                System.out.println(password);

                if (username.equals(testID) && Arrays.equals(password, testPassword.toCharArray())) {
                    JOptionPane.showMessageDialog(null, "Welcome user " + username);

                    /*Switching to Menu page
                    //new MainMenu().setVisible(true);
                    setVisible(false); */

                } else {
                    JOptionPane.showMessageDialog(null, "Wrong password or username! \nPlease try again!");
                    //System.out.println("Wrong password or username! \n Please try again!");
                }
            }
        });
        createNewAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("CreateNewAccount Pressed");
            }
        });
        resetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ResetPassword Pressed");
            }
        });
        FAQButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("FAQButton Pressed");
            }
        });
    }

    //TESTING ONLY
    public static boolean checkLoginData() {
        //Check entered user data with database
        return false;
    }
    public static void main(String[] args) {
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setAutoRequestFocus(false);
    }

}
