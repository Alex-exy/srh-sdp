package de.srh.library.ui.mainmenu;

import de.srh.library.ui.login.LoginWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MainMenu extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JLabel pageTitle;
    private JButton infosSettings;
    private JButton borrowReturn;
    private JButton browseLibraries;
    private JPanel mainMenuWindow;
    private JButton logOut;

    public MainMenu() {

        //Create main window
        setAutoRequestFocus(false);
        setContentPane(mainMenuWindow);
        setTitle("Main Menu");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        logger.info("Opening main menu ...");
        browseLibraries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        borrowReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        infosSettings.addActionListener(new ActionListener() {
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

    //Testing only
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
    }
}
