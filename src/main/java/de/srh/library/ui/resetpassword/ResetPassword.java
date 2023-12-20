package de.srh.library.ui.resetpassword;

import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.ApiResponseCode;
import de.srh.library.dto.UserDto;
import de.srh.library.service.user.UserService;
import de.srh.library.service.user.UserServiceImpl;
import de.srh.library.util.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;

public class ResetPassword extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(ResetPassword.class);
    private JPanel resetPasswordWindow;
    private JLabel pageTitle;
    private JPasswordField newPassword1;
    private JPasswordField newPassword2;
    private JButton saveButton;
    private JTextField emailCheck;
    private JLabel description;
    private JButton cancelButton;

    private UserService userService;

    public ResetPassword() {

        setAutoRequestFocus(false);
        setContentPane(resetPasswordWindow);
        setTitle("Reset Password");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        toFront();
        logger.info("Requesting password reset ...");
        userService = UserServiceImpl.createInstance();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkEnteredCredentials();
            }
        });

        //Replace with email address check from database!
        emailCheck.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                saveButton.setEnabled(true);
                emailCheck.setText("");
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public boolean checkValidEmail(String email) {
        ApiResponse<UserDto> apiResponse = userService.getUserByEmail(email);
        return apiResponse.getCode() == ApiResponseCode.SUCCESS.getCode()
                && apiResponse.getData() != null;
    }

    public void checkEnteredCredentials() {
        String email = emailCheck.getText();
        String newPasswordStr1 = String.valueOf(newPassword1.getPassword());
        String newPasswordStr2 = String.valueOf(newPassword2.getPassword());
        if (newPasswordStr1.equals(newPasswordStr2) && checkValidEmail(email) ) {
            updatePassword(PasswordUtils.hashPw(newPasswordStr1),email);
            JOptionPane.showMessageDialog(null, "Password has been successfully reset!");
            saveButton.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Passwords do not match or Email has not been registered!");
        }
    }

    public boolean updatePassword(String password, String email){
     ApiResponse<Integer> apiResponse = userService.updateUserPassword(password,email);
        return apiResponse.getCode() == ApiResponseCode.SUCCESS.getCode();

    }

    // Testing only
    public static void main(String[] args) {
        ResetPassword resetPassword = new ResetPassword();
    }

}
