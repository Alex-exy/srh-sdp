package de.srh.library.ui.edituserdata;

import de.srh.library.ui.editusers.EditUsers;
import de.srh.library.ui.login.LoginWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class EditUserData extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel editUserDataWindow;
    private JLabel pageTitle;
    private JTextField userRegistrationDate;
    private JTextField userID;
    private JTextField firstName;
    private JTextField userAddress;
    private JTextField lastName;
    private JButton saveChangesButton;
    private JTextField userContact;
    private JComboBox userRole;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel contactLabel;
    private JLabel addressLabel;
    private JLabel userIDLabel;
    private JLabel userRoleLabel;
    private JLabel userStatusLabel;
    private JLabel registrationDateLabel;
    private JComboBox userStatus;
    private JButton goBack;
    private JComboBox comboBox1;
    private JLabel userSchool;
    private JTextField userUpdateDate;
    private JLabel updateDate;

    public EditUserData() {

        setAutoRequestFocus(false);
        setContentPane(editUserDataWindow);
        setTitle("Edit User Data");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        logger.info("Opening edit user data window ...");


        saveChangesButton.addActionListener(new ActionListener() {
            //Check for changes
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Changes Saved!");
            }
        });

        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                EditUsers editUsers = new EditUsers();
                editUsers.setVisible(true);
            }
        });
    }

    public void loadCurrentUserData(String firstName, String lastName, String email, String address, String userID, char userRole, String userStatus, Date registrationDate) {
        //Load user data from database and display in fields
    }

    public static void main(String[] args) {
        EditUserData editUserData = new EditUserData();
    }

}
