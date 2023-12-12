package de.srh.library.ui.login;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;

import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.ApiResponseCode;
import de.srh.library.dto.Global;
import de.srh.library.service.user.UserService;
import de.srh.library.service.user.UserServiceImpl;
import de.srh.library.ui.createnewuser.CreateNewUser;
import de.srh.library.ui.enteremail.EnterEmail;
import de.srh.library.ui.faq.FAQ;
import de.srh.library.ui.mainmenu.MainMenu;
import de.srh.library.ui.managementmenu.ManagementMenu;
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
    private JButton forgotPasswordButton;
    private JButton faqButton;
    private JButton loginAdminButton;

    private UserService userService;

    public LoginWindow() throws HeadlessException {

        setAutoRequestFocus(false);
        setContentPane(loginWindow);
        setTitle("Login Page");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        logger.info("Opening login window ...");

        userService = UserServiceImpl.createInstance();

        //Clear field description of focus
        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("username")) {
                    usernameField.setText("");
                } else {
                }
            }
        });
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Objects.equals(String.valueOf(passwordField.getPassword()), "password")) {
                    passwordField.setText("");
                } else {
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

                ApiResponse<Long> loginResponse = userService.checkPassword(username, String.valueOf(password));
                switch (ApiResponseCode.getByCode(loginResponse.getCode())) {
                    case SUCCESS:
                        Global.userLogin(false, loginResponse.getData());
                        JOptionPane.showMessageDialog(null, "Welcome user " + username);

                        //Close login window create new main menu
                        dispose();
                        MainMenu mainMenu = new MainMenu();
                        mainMenu.setVisible(true);
                        // ! Save user login information (Temporary save logged-in user until log out in main menu window)
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, loginResponse.getCode() + ": " + loginResponse.getMessage());
                        break;
                }
            }
        });
        loginAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Global.isAdmin = true;
                logger.info("LOGGED IN AS: ADMINISTRATOR");
                System.out.println("IS: " + Global.isAdmin);

                //Check database for admin login data
                dispose();
                ManagementMenu managementMenu = new ManagementMenu();
                managementMenu.setVisible(true);
            }
        });
        createNewAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CreateNewUser createNewUser = new CreateNewUser();
                createNewUser.setVisible(true);
            }
        });
        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnterEmail enterEmail = new EnterEmail();
                enterEmail.setVisible(true);
            }
        });
        faqButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FAQ faq = new FAQ();
                faq.setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        new LoginWindow();
    }
}

