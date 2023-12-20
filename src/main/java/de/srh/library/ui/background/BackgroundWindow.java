package de.srh.library.ui.background;

import de.srh.library.ui.createnewuser.CreateNewUser;
import de.srh.library.ui.login.LoginWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BackgroundWindow extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);

    private JPanel backgroundWindow;
    private JLabel backgroundImage;

    private JLayeredPane layeredPane;

    public BackgroundWindow() {

        setAutoRequestFocus(false);
        setContentPane(backgroundWindow);
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        logger.info("Opening background ...");


        //JLayeredPane layeredPane =new JLayeredPane();
        layeredPane.setBounds(0,0,1280,720);
        JFrame frame = new JFrame("JLayerdPane");
        frame.add(layeredPane);
        layeredPane.add(backgroundImage, JLayeredPane.DEFAULT_LAYER);
        setContentPane(frame);


    }

/*
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        CreateNewUser createNewUser = new CreateNewUser();
        createNewUser.setVisible(true);
    }
*/
    public static void main(String[] args) {
        BackgroundWindow backgroundWindow = new BackgroundWindow();
    }

    private void createUIComponents() {
        // place custom component creation code here



        //backgroundImage = new JLabel(new ImageIcon("images/LibraryBackground.png"));
    }
}
