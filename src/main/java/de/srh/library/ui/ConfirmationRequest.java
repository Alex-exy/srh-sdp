package de.srh.library.ui;
import de.srh.library.ui.login.LoginWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.*;

public class ConfirmationRequest extends WindowAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    public boolean userDecision = false;
    JFrame f;
    public ConfirmationRequest(){
        requestConfirmation();
    }

    public void requestConfirmation(){
        int a = JOptionPane.showConfirmDialog(f, "Are you sure?", "Please Confirm!", JOptionPane.YES_NO_OPTION);
        if(a == JOptionPane.YES_OPTION) {
            userDecision = true;
            logger.info("Confirmed by user . . .");
        }
        else {
            userDecision = false;
            logger.info("Canceled by user . . .");
        }
    }
    public static void main(String[] args) {
        new ConfirmationRequest();
    }
}
