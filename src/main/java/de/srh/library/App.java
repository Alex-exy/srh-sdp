package de.srh.library;

import de.srh.library.ui.login.LoginWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Running point of the application
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Library Management System is starting...");
        new LoginWindow();
    }
}
