package de.srh.library.ui.managementmenu;

import de.srh.library.ui.login.LoginWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagementMenu extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel managementWindow;
    private JLabel pageTitle;
    private JButton logOut;
    private JButton userManagementButton;
    private JButton bookManagementButton;
    private JButton borrowAndReturnManagementButton;

    public ManagementMenu() {

        setAutoRequestFocus(false);
        setContentPane(managementWindow);
        setTitle("Management Menu");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        logger.info("Opening manage menu window ...");
        userManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        bookManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        borrowAndReturnManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginWindow loginWindow = new LoginWindow();
                loginWindow.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        ManagementMenu managementMenu = new ManagementMenu();
    }
}
