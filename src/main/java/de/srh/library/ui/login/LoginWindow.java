package de.srh.library.ui.login;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Objects;
import javax.swing.*;

import de.srh.library.ui.mainmenu.MainMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginWindow extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel loginWindow;
    private JLabel pageTitle;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JLabel loginTitle;
    private JButton loginButton;
    private JButton createNewAccountButton;
    private JButton resetPasswordButton;
    private JButton faqButton;

    public LoginWindow() throws HeadlessException {

        //Create login window
        setAutoRequestFocus(false);
        setContentPane(loginWindow);
        setTitle("Please Login");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        logger.info("Starting login window ...");

        //Example user data for testing
        String testID = "asdf";
        String testPassword = "asdf";

        //Clear field description of focus
        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("username")) {
                    usernameField.setText("");
                }
                else {
                }
            }
        });
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Objects.equals(Arrays.toString(passwordField.getPassword()), "password")) {
                    passwordField.setText("");
                }
                else {
                }
            }
        });


        //Login button action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameField.getText();
                System.out.println(username);
                char[] password = passwordField.getPassword();
                System.out.println(password);

                // ! Missing: Check for valid user data (Database?)
                if (username.equals(testID) && Arrays.equals(password, testPassword.toCharArray())) {
                    JOptionPane.showMessageDialog(null, "Welcome user " + username);

                    //Close login window create new main menu
                    dispose();
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.setVisible(true);
                    // ! Save user login information (Save logged-in user)

                } else {
                    JOptionPane.showMessageDialog(null, "Wrong password or username! \nPlease try again!");
                }
            }
        });

        //Change to associated new menus
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
        faqButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("faqButton Pressed");
            }
        });
    }

    //Testing only:
    public static boolean checkLoginData() {
        //Check entered user data with database
        return false;
    }
    public static void main(String[] args) {
        LoginWindow loginWindow = new LoginWindow();
    }
}

