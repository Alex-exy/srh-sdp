package de.srh.library.ui.infossettings;

import de.srh.library.ui.login.LoginWindow;
import de.srh.library.ui.mainmenu.MainMenu;
import de.srh.library.ui.resetpassword.ResetPassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfosSettings extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel infosSettingsWindow;
    private JLabel pageTitle;
    private JTextField userEmail;
    private JTextField userAddress;
    private JTextField userID;
    private JTextField userRole;
    private JButton changeInformationButton;
    private JButton changePasswordButton;
    private JLabel emailLabel;
    private JLabel addressLabel;
    private JLabel userIDLabel;
    private JLabel roleLabel;
    private JButton safeButton;
    private JButton returnButton;

    //Testing only
    private String testemail = "test.test@gmail.com";
    private String testaddress = "hauptstraße 3";
    private long testid = 110110110;
    private String testrole = "gigachad";

    public InfosSettings() {

        setAutoRequestFocus(false);
        setContentPane(infosSettingsWindow);
        setTitle("Infos and Settings");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        logger.info("Opening infos and settings window ...");

        // ! Fill with correct user data from saved users in database
        initUserInformation(testemail, testaddress, testid, testrole);

        changeInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                safeButton.setEnabled(true);
                userEmail.setEditable(true);
                userAddress.setEditable(true);
            }
        });
        safeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent safe) {
                userEmail.setEditable(false);
                userAddress.setEditable(false);
                safeButton.setEnabled(false);
                //Save new user data, overwrite old user data from database
                // ! Check for valid data input !
                updateUserInformation(userEmail.getText(), userAddress.getText(), Long.parseLong(userID.getText()), userRole.getText());
            }
        });
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetPassword resetPassword = new ResetPassword();
                resetPassword.setVisible(true);
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true);
            }
        });
    }

    public void initUserInformation(String currentUserEmail, String currentUserAddress, long currentUserNumber, String currentUserRole) {
        userEmail.setText(currentUserEmail);
        userAddress.setText(currentUserAddress);
        userID.setText(String.valueOf(currentUserNumber));
        userRole.setText(currentUserRole);
    }

    ;

    public void updateUserInformation(String newUserEmail, String newUserAddress, long newUserNumber, String newUserRole) {
        userEmail.setText(newUserEmail);
        userAddress.setText(newUserAddress);
        userID.setText(String.valueOf(newUserNumber));
        userRole.setText(newUserRole);
    }

    ;

    //Testing Only
    public static void main(String[] args) {
        InfosSettings infosSettings = new InfosSettings();
    }
}
