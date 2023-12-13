package de.srh.library.ui.editbookdata;

import de.srh.library.ui.login.LoginWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBookData extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel editBookDataWindow;
    private JButton cancelButton;
    private JLabel pageTitle;
    private JButton saveChangesButton;
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
    private static String testid = "123";

    public EditBookData(String bookID) {

        setAutoRequestFocus(false);
        setContentPane(editBookDataWindow);
        setTitle("Edit Book Data");
        setSize(720, 1000);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        logger.info("Opening edit book data window ...");

        loadCurrentBookData();

        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBookData();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void loadCurrentBookData() {

    }
    public void updateBookData() {
        //Check valid data input
        //Set new examplefield.getText() = saved database data . . .
    }

    public static void main(String[] args) {
        new EditBookData(testid);
    }
}
