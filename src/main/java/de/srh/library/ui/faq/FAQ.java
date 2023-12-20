package de.srh.library.ui.faq;

import de.srh.library.ui.login.LoginWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FAQ extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel faqWindow;
    private JLabel pageTitle;
    private JButton returnButton;
    private JTextArea faqTextBox;

    public FAQ() {

        setAutoRequestFocus(false);
        setContentPane(faqWindow);
        setTitle("FAQ");
        setSize(720, 1000);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        toFront();
        logger.info("Displaying FAQs ...");

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    dispose();
            }
        });
    }

    public static void main(String[] args) {
        FAQ faq = new FAQ();
    }

}
