package de.srh.library.ui.mainmenu;

import de.srh.library.ui.borrowreturn.BorrowReturn;
import de.srh.library.ui.infossettings.InfosSettings;
import de.srh.library.ui.login.LoginWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
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

        setAutoRequestFocus(false);
        setContentPane(mainMenuWindow);
        setTitle("Main Menu");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        logger.info("Opening main menu window ...");
        browseLibraries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        borrowReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                BorrowReturn borrowReturn = new BorrowReturn();
                borrowReturn.setVisible(true);
            }
        });
        infosSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                InfosSettings infosSettings = new InfosSettings();
                infosSettings.setVisible(true);
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
