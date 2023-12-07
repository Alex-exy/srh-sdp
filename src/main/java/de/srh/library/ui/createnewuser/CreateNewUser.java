package de.srh.library.ui.createnewuser;

import de.srh.library.ui.login.LoginWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class CreateNewUser extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel createNewUser;
    private JLabel pageTitle;
    private JTextField enterFirstName;
    private JTextField enterSchoolID;
    private JTextField enterAddress;
    private JTextField enterEmail;
    private JComboBox selectRole;
    private JTextField enterLastName;
    private JLabel labelFirstName;
    private JLabel labelLastName;
    private JLabel labelEmail;
    private JLabel labelAddress;
    private JLabel labelSchoolID;
    private JLabel labelRole;
    private JButton buttonContinue;

    public CreateNewUser() {

        selectRole.addItem("Nothing Selected");
        selectRole.addItem("Heidelberg University Library");
        selectRole.addItem("Stadtbücherei Heidelberg");
        selectRole.addItem("Hauptbibliothek Heidelberg");

        setAutoRequestFocus(false);
        setContentPane(createNewUser);
        setTitle("New User Registration");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        logger.info("Opening create new user window ...");

        buttonContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginWindow loginWindow = new LoginWindow();
                loginWindow.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        CreateNewUser createNewUser = new CreateNewUser();
    }
}
