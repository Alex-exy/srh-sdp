package de.srh.library.ui.infossettings;

import cn.hutool.core.exceptions.ValidateException;
import de.srh.library.constant.UserRole;
import de.srh.library.dto.*;
import de.srh.library.entity.User;
import de.srh.library.service.user.UserService;
import de.srh.library.service.user.UserServiceImpl;
import de.srh.library.ui.login.LoginWindow;
import de.srh.library.ui.mainmenu.MainMenu;
import de.srh.library.ui.resetpassword.ResetPassword;
import de.srh.library.util.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class InfosSettings extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel infosSettingsWindow;
    private JLabel pageTitle;
    private JTextField userEmail;
    private JTextField userAddress;
    private JTextField userID;
    private JTextField userRole;
    private JButton changeInformationButton;
    private JButton changePasswordButton;
    private JLabel emailLabel;
    private JLabel addressLabel;
    private JLabel userIDLabel;
    private JLabel roleLabel;
    private JButton saveButton;
    private JButton returnButton;
    private JTextField userLastName;
    private JTextField userFirstName;
    private JComboBox userSelectSchool;
    private JLabel accountStatus;
    private JTextField accountStatusField;
    private long userId = Global.loggedInUserId;
    private UserService userService;
    Map<String, Integer> schoolsMap;




    public InfosSettings() {

        setAutoRequestFocus(false);
        setContentPane(infosSettingsWindow);
        setTitle("Infos and Settings");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        logger.info("Opening infos and settings window ...");

        changeInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButton.setEnabled(true);
                userFirstName.setEditable(true);
                userLastName.setEditable(true);
                userEmail.setEditable(true);
                userAddress.setEditable(true);
                userSelectSchool.setEnabled(true);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent safe) {
                try{
                    inputValidation();
                }catch (ValidateException ve ){
                    JOptionPane.showMessageDialog(null, ve.getMessage());
                    return;
                }
                userService = UserServiceImpl.createInstance();
                updateUserInfo(userId);
            }
        });
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetPassword resetPassword = new ResetPassword();
                resetPassword.setVisible(true);
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true);
            }
        });
    }


    public void loadUserInformation(UserDto userDto) {

        userService = UserServiceImpl.createInstance();
        getSchools();

        for (UserRole role : UserRole.values()) {
            userRole.setText(role.getRoleName());
        }

        UserDto userDtoNew = userService.getUserById(userDto.getUserId()).getData();
        userFirstName.setText(userDto.getFirstName());
        userLastName.setText(userDto.getFamilyName());
        accountStatusField.setText(
                switch (userDto.getUserStatus()){
                    case "F" -> "Frozen";
                    case "A" -> "Active";
                    case "O" -> "Overdue";
                    case "I" -> "Inactive";
                    default -> "None";
                });
        userRole.setText(
                switch (userDto.getUserRole()) {
                    case "S" -> "Student";
                    case "T" -> "Teacher";
                    default -> "None";
                });
        userEmail.setText(userDto.getEmail());
        userSelectSchool.setSelectedItem(userSchoolName(userDtoNew.getUserId()));
        userAddress.setText(userDto.getAddress());
        userID.setText(String.valueOf(userDto.getUserId()));

    }




    private ApiResponse updateUserInfo(Long userId) {
        User user = new User();

        user.setUserId(userId);
        user.setFirstName(userFirstName.getText());
        user.setSchoolId(schoolsMap.get(userSelectSchool.getSelectedItem().toString()));
        user.setFamilyName(userLastName.getText());
        user.setEmail(userEmail.getText());
        user.setAddress(addressLabel.getText());
        user.setUserRole(
                switch(userRole.getText().toString()){
                    case "Student" -> "S";
                    case "Teacher" -> "T";
                    default -> "None";
                });
        user.setUserStatus(
        switch (accountStatusField.getText().toString()){
            case "Frozen"   -> "F";
            case "Active"   -> "A";
            case "Overdue"  -> "O";
            case "Inactive" -> "I";
            default -> "None";
        });
        user.setUpdateDate(new Date());
        return userService.updateUserData(user);

    }


    private String userSchoolName(long userId){
        userService = UserServiceImpl.createInstance();
        UserDto userDto = new UserDto();
        return userDto.getSchoolName(userId);
    }
    public void getSchools(){
        userService = UserServiceImpl.createInstance();
        ApiResponse<Map<String, Integer>> apiResponseLibrary = userService.getAllSchools();
        if (ApiResponseCode.SUCCESS.getCode() == apiResponseLibrary.getCode()) {
            schoolsMap = apiResponseLibrary.getData();

            TreeMap<String, Integer> sortedLibrariesMap = new TreeMap<>(schoolsMap);
            userSelectSchool.removeAllItems();
            sortedLibrariesMap.forEach((s, i) -> userSelectSchool.addItem(s));
        }
    }
    public void inputValidation(){
        ValidatorUtils.validateFirstName(userFirstName.getText());
        ValidatorUtils.validateLastName(userLastName.getText());
        ValidatorUtils.validateEmail(userEmail.getText());
        ValidatorUtils.validateAddress(addressLabel.getText());

    }

    //Testing Only
    public static void main(String[] args) {
        InfosSettings infosSettings = new InfosSettings();
    }
}
