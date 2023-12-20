package de.srh.library.ui.managementmenu;

import de.srh.library.cache.Global;
import de.srh.library.ui.ConfirmationRequest;
import de.srh.library.ui.editbooks.EditBooks;
import de.srh.library.ui.editusers.EditUsers;
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
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        toFront();
        logger.info("Opening manage menu window ...");
        userManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                EditUsers editUsers = new EditUsers();
                editUsers.setVisible(true);
            }
        });
        bookManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                EditBooks editBooks = new EditBooks();
                editBooks.setVisible(true);
            }
        });
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfirmationRequest confirmation = new ConfirmationRequest();
                if(confirmation.userDecision) {
                    Global.userLogOut();
                    dispose();
                    LoginWindow loginWindow = new LoginWindow();
                    loginWindow.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Logged out!");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Logout canceled!");
                }
            }
        });
    }
    public static void main(String[] args) {
        ManagementMenu managementMenu = new ManagementMenu();
    }
}
