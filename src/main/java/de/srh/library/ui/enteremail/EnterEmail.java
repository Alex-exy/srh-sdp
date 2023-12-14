package de.srh.library.ui.enteremail;

import cn.hutool.core.collection.ListUtil;
import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.ApiResponseCode;
import de.srh.library.entity.User;
import de.srh.library.service.user.UserService;
import de.srh.library.service.user.UserServiceImpl;
import de.srh.library.util.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class EnterEmail extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(EnterEmail.class);
    private JPanel enterEmailWindow;
    private JTextField email;
    private JButton resetPasswordButton;
    private JLabel pageTitle;

    private UserService userService;

    public EnterEmail() {

        setAutoRequestFocus(false);
        setContentPane(enterEmailWindow);
        setTitle("Reset Password");
        setSize(600, 400);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        logger.info("Request password reset via email ...");
        userService = UserServiceImpl.createInstance();
        resetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailAddress = email.getText();
                if (checkValidEmail(emailAddress)) {
                    sendPasswordResetEmail(emailAddress);
                    JOptionPane.showMessageDialog(null, "Email sent!");
                    resetPasswordButton.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Email not registered! \nPlease try again!");
                    resetPasswordButton.setEnabled(true);
                }
            }
        });
        email.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                resetPasswordButton.setEnabled(true);
            }
        });
    }

    private void sendPasswordResetEmail(String emailAddress) {
        EmailSender.send(ListUtil.toList(emailAddress),
                "Reset Password - Heidelberg Library",
                "Click <a href = https://github.com/Alex-exy/srh-sdp/> here</a> to set a new password.",
                "password-reset-mail.html",
                true);
    }

    public boolean checkValidEmail(String email) {
        ApiResponse<User> apiResponse = userService.getUserByEmail(email);
        if (apiResponse.isSuccess() && apiResponse.getData() != null){
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        EnterEmail enterEmail = new EnterEmail();
    }
}
