package de.srh.library.ui.createnewuser;

import de.srh.library.constant.UserRole;
import de.srh.library.constant.UserStatus;
import de.srh.library.entity.dto.ApiResponse;
import de.srh.library.entity.dto.ApiResponseCode;
import de.srh.library.entity.User;
import de.srh.library.service.user.UserService;
import de.srh.library.service.user.UserServiceImpl;
import de.srh.library.ui.login.LoginWindow;
import de.srh.library.util.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CreateNewUser extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(CreateNewUser.class);
    private JPanel createNewUser;
    private JLabel pageTitle;
    private JTextField enterFirstName;
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
    private JComboBox selectSchool;
    private JTextField enterPassword;
    private JLabel lableEnterPassword;
    private JLabel lableReenterPassword;
    private JTextField reenterPassword;

    private UserService userService;
    private Map<String, Integer> schoolsMap;

    public CreateNewUser() {

        for (UserRole role : UserRole.values()) {
            selectRole.addItem(role.getRoleName());
        }

        userService = UserServiceImpl.createInstance();
        ApiResponse<HashMap<String, Integer>> apiResponse = userService.getAllSchools();
        if (ApiResponseCode.SUCCESS.getCode() == apiResponse.getCode()){
            schoolsMap = apiResponse.getData();
            schoolsMap.forEach((s, i) -> selectSchool.addItem(s));
        }

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
                //TODO: input data validation

                createUser();
                // TODO: add a dialog
                dispose();
                LoginWindow loginWindow = new LoginWindow();
                loginWindow.setVisible(true);
            }
        });
    }

    private void createUser() {
        //Save a user
        User newUser = new User();
        newUser.setUserRole(UserRole.getByRoleName(selectRole.getSelectedItem().toString()).getRoleCode());
        newUser.setAddress(enterAddress.getText());
        newUser.setEmail(enterEmail.getText());
        newUser.setFirstName(enterFirstName.getText());
        newUser.setFamilyName(enterLastName.getText());
        newUser.setSchoolId(schoolsMap.get(selectSchool.getSelectedItem().toString()));
        //TODO: set a default password for test, a password field needs to be added
        newUser.setPasswordHash(PasswordUtils.hashPw("000000"));
        newUser.setUserStatus(UserStatus.ACTIVE.getUserStatusCode());
        userService.createUser(newUser);
    }

    public static void main(String[] args) {
        CreateNewUser createNewUser = new CreateNewUser();
    }
}
