package de.srh.library.ui.edituserdata;

import de.srh.library.constant.UserRole;
import de.srh.library.constant.UserStatus;
import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.ApiResponseCode;
import de.srh.library.dto.UserDto;
import de.srh.library.entity.User;
import de.srh.library.service.user.UserService;
import de.srh.library.service.user.UserServiceImpl;
import de.srh.library.ui.editusers.EditUsers;
import de.srh.library.ui.login.LoginWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Map;

public class EditUserData extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JPanel editUserDataWindow;
    private JLabel pageTitle;
    private JTextField userRegistrationDate;
    private JTextField userID;
    private JTextField firstName;
    private JTextField userAddress;
    private JTextField lastName;
    private JButton saveChangesButton;
    private JTextField userEmail;
    private JComboBox userRole;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel contactLabel;
    private JLabel addressLabel;
    private JLabel userIDLabel;
    private JLabel userRoleLabel;
    private JLabel userStatusLabel;
    private JLabel registrationDateLabel;
    private JComboBox userStatus;
    private JButton goBack;
    private JComboBox school;
    private JLabel userSchool;
    private JTextField userUpdateDate;
    private JLabel updateDate;
    private JButton editDataButton;
    private JButton deleteUserButton;
    private JButton saveButton;
    private static UserService userService;
    private Map<String, Integer> schoolsMap;


    public EditUserData(UserDto user) {

        userService = UserServiceImpl.createInstance();


        setAutoRequestFocus(false);
        setContentPane(editUserDataWindow);
        setTitle("Edit User Data");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        logger.info("Opening edit user data window ...");
        long userId = user.getUserId();
        loadCurrentUserData(user);

        UserDto userDto = userService.getUserById(userId).getData();


        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Changes Saved!");
                updateUserData(userId);
                saveChangesButton.setEnabled(false);
            }
        });
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            userService = UserServiceImpl.createInstance();
            userService.removeUser(userId);
            }
        });
        editDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChangesButton.setEnabled(true);
            }
        });
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                EditUsers editUsers = new EditUsers();
                editUsers.setVisible(true);
            }
        });
    }

    public void loadCurrentUserData(UserDto user) {
        userService = UserServiceImpl.createInstance();
        getSchools();

        for (UserRole role : UserRole.values()) {
            userRole.addItem(role.getRoleName());
        }
        for (UserStatus status : UserStatus.values()){
            userStatus.addItem(status.getUserStatusName());
        }

        UserDto userDto = userService.getUserById(user.getUserId()).getData();
        firstName.setText(userDto.getFirstName());
        lastName.setText(userDto.getFamilyName());
        userStatus.setSelectedItem(
            switch (userDto.getUserStatus()){
            case "F" -> "Frozen";
            case "A" -> "Active";
            case "O" -> "Overdue";
            case "I" -> "Inactive";
            default -> "None";
        });
       userRole.setSelectedItem(
            switch (userDto.getUserRole()) {
            case "S" -> "Student";
            case "T" -> "Teacher";
            default -> "None";
       });
        userEmail.setText(userDto.getEmail());
        school.setSelectedItem(userSchoolName(user.getUserId()));
        userAddress.setText(userDto.getAddress());
        userID.setText(String.valueOf(userDto.getUserId()));
        userRegistrationDate.setText(userDto.getRegistrationDate().toString());
        userUpdateDate.setText(userDto.getUpdateDate().toString());

    }
    private ApiResponse updateUserData(long userId){
        User user = new User();

        user.setUserId(userId);
        user.setFirstName(firstName.getText());
        user.setSchoolId(schoolsMap.get(school.getSelectedItem().toString()));
        user.setUserRole(
            switch(userRole.getSelectedItem().toString()){
            case "Student" -> "S";
            case "Teacher" -> "T";
            default -> "None";
        });
        user.setUserStatus(
            switch (userStatus.getSelectedItem().toString()){
                case "Frozen"   -> "F";
                case "Active"   -> "A";
                case "Overdue"  -> "O";
                case "Inactive" -> "I";
            default -> "None";
        });
        user.setFamilyName(lastName.getText());
        user.setEmail(userEmail.getText());
        user.setAddress(addressLabel.getText());
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
        ApiResponse<Map<String, Integer>> apiResponse = userService.getAllSchools();
        if (ApiResponseCode.SUCCESS.getCode() == apiResponse.getCode()){
            schoolsMap = apiResponse.getData();
            schoolsMap.forEach((s, i) -> school.addItem(s));
        }    }

    public static void main(String[] args) {
        userService = UserServiceImpl.createInstance();
        UserDto user = userService.getUserById(1000L).getData();
        new EditUserData(user);
    }

}
