package de.srh.library.ui.addnewbook;

import de.srh.library.ui.editbooks.EditBooks;
import de.srh.library.ui.login.LoginWindow;
import de.srh.library.ui.managementmenu.ManagementMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewBook extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel addNewBookWindow;
    private JButton cancelButton;
    private JLabel pageTitle;
    private JButton saveAndAddBookButton;
    private JTextField doiField;
    private JTextField libraryIDField;
    private JTextField updateDateField;
    private JTextField addDateField;
    private JTextField descriptionField;
    private JTextField priceField;
    private JTextField genreField;
    private JTextField authorField;
    private JTextField publishDateField;
    private JTextField isbnField;
    private JTextField languageField;
    private JTextField subtitleField;
    private JTextField titleField;

    public AddNewBook() {

        setAutoRequestFocus(false);
        setContentPane(addNewBookWindow);
        setTitle("Add New Book");
        setSize(720, 1000);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        logger.info("Opening add new book window ...");


        saveAndAddBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Database logic - add book
                //Check valid inputs
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args){
        new AddNewBook();
    }

}
