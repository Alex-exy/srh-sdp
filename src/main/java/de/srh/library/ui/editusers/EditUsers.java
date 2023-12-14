package de.srh.library.ui.editusers;

import de.srh.library.ui.edituserdata.EditUserData;
import de.srh.library.ui.login.LoginWindow;
import de.srh.library.ui.managementmenu.ManagementMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class EditUsers extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(EditUsers.class);
    private JPanel editUsersWindow;
    private JLabel pageTitle;
    private JButton goBack;
    private JTextField searchByUserIDTextField;
    private JTextField searchByUserEmailTextField;
    private JButton searchUserButton;
    private JButton editUserButton;

    public EditUsers() {

        setAutoRequestFocus(false);
        setContentPane(editUsersWindow);
        setTitle("Edit User Menu");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        logger.info("Opening edit user window ...");

        searchUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchUserButton.setEnabled(false);
                //Valid user check
                JOptionPane.showMessageDialog(null, "User found!");
                editUserButton.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Credentials wrong or user nonexistent!");
            }
        });
        editUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditUserData editUserData = new EditUserData();
                editUserData.setVisible(true);
                //Valid user check
                /*
                dispose();
                edit user information menu
                */
            }
        });
        searchByUserIDTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                searchUserButton.setEnabled(true);
            }
        });
        searchByUserEmailTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                searchUserButton.setEnabled(true);
            }
        });
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ManagementMenu managementMenu = new ManagementMenu();
                managementMenu.setVisible(true);
            }
        });
        searchByUserIDTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                searchByUserIDTextField.setText("");
            }
        });
        searchByUserEmailTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                searchByUserEmailTextField.setText("");
            }
        });
    }

    public static void main(String[] args) {
        EditUsers editUser = new EditUsers();
    }
}
