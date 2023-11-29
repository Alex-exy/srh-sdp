package de.srh.library.ui.login;

import java.awt.*;
import javax.swing.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginWindow extends JFrame {

  private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);

  public LoginWindow() throws HeadlessException {
    super("Login");
    this.setSize(1000, 800);
    this.setVisible(true);
    logger.info("Starting login window...");
  }
}
