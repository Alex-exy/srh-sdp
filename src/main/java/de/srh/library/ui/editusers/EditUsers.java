package de.srh.library.ui.editusers;

import cn.hutool.core.exceptions.ValidateException;
import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.UserDto;
import de.srh.library.entity.User;
import de.srh.library.service.user.UserService;
import de.srh.library.service.user.UserServiceImpl;
import de.srh.library.ui.edituserdata.EditUserData;
import de.srh.library.ui.managementmenu.ManagementMenu;
import de.srh.library.util.ValidatorUtils;
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
    private UserService userService;


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
                userService = UserServiceImpl.createInstance();

                if (searchByUserEmailTextField.getText().trim().isEmpty() && !searchByUserIDTextField.getText().trim().isEmpty()) {
                    try {
                        ValidatorUtils.validateUserId(searchByUserIDTextField.getText());
                    } catch (ValidateException ve) {
                        JOptionPane.showMessageDialog(null, ve.getMessage());
                        return;
                    }
                    long userId = Long.parseLong(searchByUserIDTextField.getText().trim());
                    UserDto userById = userService.getUserById(userId).getData();
                    if (userFoundId(userId)) {
                        dispose();
                        EditUserData editUserData = new EditUserData(userById);
                        editUserData.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "User does not exist! \nPlease try again!");
                    }
                } else if (searchByUserIDTextField.getText().trim().isEmpty() && !searchByUserEmailTextField.getText().trim().isEmpty()) {
                    try {
                        ValidatorUtils.validateEmail(searchByUserEmailTextField.getText());
                    } catch (ValidateException ve) {
                        JOptionPane.showMessageDialog(null, ve.getMessage());
                        return;
                    }
                    String userEmailText = searchByUserEmailTextField.getText().trim();
                    UserDto userByEmail = userService.getUserByEmail(userEmailText).getData();
                    if (userFoundEmail(userEmailText)) {
                        dispose();
                        EditUserData editUserData = new EditUserData(userByEmail);
                        editUserData.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "User does not exist! \nPlease try again!");
                    }
                } else if (searchByUserIDTextField.getText().trim().isEmpty() && searchByUserEmailTextField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fields can not be empty!");
                } else {
                    System.out.println("Failed");
                }


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

    public boolean userFoundId(long userId) {
        return userService.userFoundId(Long.parseLong(searchByUserIDTextField.getText())).getData() == 1;
    }

    public boolean userFoundEmail(String email) {
        return userService.userFoundEmail(searchByUserEmailTextField.getText()).getData() == 1;
    }

    public static void main(String[] args) {

        EditUsers editUser = new EditUsers();
    }
}
