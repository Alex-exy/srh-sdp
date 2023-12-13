package de.srh.library.ui.createnewuser;

import cn.hutool.core.exceptions.ValidateException;
import de.srh.library.constant.UserRole;
import de.srh.library.constant.UserStatus;
import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.ApiResponseCode;
import de.srh.library.entity.User;
import de.srh.library.service.user.UserService;
import de.srh.library.service.user.UserServiceImpl;
import de.srh.library.ui.login.LoginWindow;
import de.srh.library.util.PasswordUtils;
import de.srh.library.util.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JPasswordField enterPassword;
    private JLabel lableEnterPassword;
    private JLabel lableReenterPassword;
    private JPasswordField reenterPassword;
    private JCheckBox agreementCheckBox;

    private UserService userService;
    private Map<String, Integer> schoolsMap;

    public CreateNewUser() {

        for (UserRole role : UserRole.values()) {
            selectRole.addItem(role.getRoleName());
        }

        userService = UserServiceImpl.createInstance();
        ApiResponse<Map<String, Integer>> apiResponse = userService.getAllSchools();
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
                try{
                    inputDataValidation();
                }catch (ValidateException ve ){
                    JOptionPane.showMessageDialog(null, ve.getMessage());
                    return;
                }
                ApiResponse response = createUser();
                if (ApiResponseCode.SUCCESS.getCode() == response.getCode()){
                    JOptionPane.showMessageDialog(null, "Success!");
                    dispose();
                    LoginWindow loginWindow = new LoginWindow();
                    loginWindow.setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null, response.getCode() + response.getMessage());
                }
            }
        });
        agreementCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (agreementCheckBox.isSelected()) {
                    buttonContinue.setEnabled(true);
                }
            }
        });
    }

    private ApiResponse createUser() {
        User newUser = new User();
        newUser.setUserRole(UserRole.getByRoleName(selectRole.getSelectedItem().toString()).getRoleCode());
        newUser.setAddress(enterAddress.getText());
        newUser.setEmail(enterEmail.getText());
        newUser.setFirstName(enterFirstName.getText());
        newUser.setFamilyName(enterLastName.getText());
        newUser.setSchoolId(schoolsMap.get(selectSchool.getSelectedItem().toString()));
        newUser.setPasswordHash(PasswordUtils.hashPw(enterPassword.getText()));
        newUser.setUserStatus(UserStatus.ACTIVE.getUserStatusCode());
        return userService.createUser(newUser);
    }

    private void inputDataValidation(){
        ValidatorUtils.validateEmail(enterEmail.getText());
        ValidatorUtils.validatePassword(enterPassword.getText(), reenterPassword.getText());
        ValidatorUtils.validateName(enterFirstName.getText());
        ValidatorUtils.validateName(enterLastName.getText());
        ValidatorUtils.validateAddress(enterAddress.getText());
    }

    public static void main(String[] args) {
        CreateNewUser createNewUser = new CreateNewUser();
    }
}
