package de.srh.library.ui.login;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;

import cn.hutool.core.exceptions.ValidateException;
import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.ApiResponseCode;
import de.srh.library.dto.Global;
import de.srh.library.service.admin.AdminService;
import de.srh.library.service.admin.AdminServiceImpl;
import de.srh.library.service.user.UserService;
import de.srh.library.service.user.UserServiceImpl;
import de.srh.library.ui.createnewuser.CreateNewUser;
import de.srh.library.ui.enteremail.EnterEmail;
import de.srh.library.ui.faq.FAQ;
import de.srh.library.ui.mainmenu.MainMenu;
import de.srh.library.ui.managementmenu.ManagementMenu;
import de.srh.library.util.ValidatorUtils;
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
    private AdminService adminService;

    public LoginWindow() throws HeadlessException {

        setAutoRequestFocus(false);
        setContentPane(loginWindow);
        setTitle("Login Page");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        logger.info("Opening login window ...");

        userService = UserServiceImpl.createInstance();
        adminService = AdminServiceImpl.createInstance();

        //Login button action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                logger.debug("username: " + username);
                char[] password = passwordField.getPassword();

                try{
                    ValidatorUtils.validateEmail(usernameField.getText());
                }catch (ValidateException ve ){
                    JOptionPane.showMessageDialog(null, ve.getMessage());
                    return;
                }

                ApiResponse<Long> loginResponse = userService.checkPassword(username, String.valueOf(password));
                switch (ApiResponseCode.getByCode(loginResponse.getCode())) {
                    case SUCCESS:
                        Global.userLogin(loginResponse.getData());
                        JOptionPane.showMessageDialog(null, "Welcome user " + username);

                        dispose();
                        MainMenu mainMenu = new MainMenu();
                        mainMenu.setVisible(true);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,
                                loginResponse.getCode() + ": " + loginResponse.getMessage());
                        break;
                }
            }
        });
        loginAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ValidatorUtils.validateAdmin(usernameField.getText());
                }catch (ValidateException ve ){
                    JOptionPane.showMessageDialog(null, ve.getMessage());
                    return;
                }
                String adminUserName = usernameField.getText();
                String adminPassword = String.valueOf(passwordField.getPassword());

                ApiResponse loginResponse = adminService.checkPassword(adminUserName,adminPassword);

                switch (ApiResponseCode.getByCode(loginResponse.getCode())) {
                    case SUCCESS:
                        Global.adminLogin();
                        JOptionPane.showMessageDialog(null, "Welcome user " + adminUserName);
                        dispose();
                        ManagementMenu managementMenu = new ManagementMenu();
                        managementMenu.setVisible(true);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,
                                loginResponse.getCode() + ": " + loginResponse.getMessage());
                        break;
                }
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

