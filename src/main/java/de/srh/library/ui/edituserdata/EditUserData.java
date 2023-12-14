package de.srh.library.ui.edituserdata;

import de.srh.library.dto.Global;
import de.srh.library.entity.User;
import de.srh.library.ui.editusers.EditUsers;
import de.srh.library.ui.login.LoginWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditUserData extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel editUserDataWindow;
    private JLabel edituserdataTitle;
    private JTextField userRegistrationDate;
    private JTextField userID;
    private JTextField firstName;
    private JTextField userAddress;
    private JTextField lastName;
    private JButton savedataButton;
    private JTextField userContact;
    private JComboBox userRole;
    private JLabel firstnameLable;
    private JLabel lastnameLable;
    private JLabel contactLable;
    private JLabel addressLable;
    private JLabel useridLable;
    private JLabel userroleLable;
    private JLabel userstatusLable;
    private JLabel registrationdateLable;
    private JComboBox userStatus;
    private JButton backButton;
    private long userId = Global.loggedInUserId;

    public EditUserData() {

        setAutoRequestFocus(false);
        setContentPane(editUserDataWindow);
        setTitle("Edit User Data");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        logger.info("Opening edit user data window ...");


        savedataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savedataButton.setEnabled(false);
                JOptionPane.showMessageDialog(null, "User Data Saved!");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                EditUsers editUsers = new EditUsers();
                editUsers.setVisible(true);
            }
        });


    }
    public void updateUserData(long userId){
        User user = new User();
    }



    public static void main(String[] args) {
        EditUserData editUserData = new EditUserData();
    }
}
