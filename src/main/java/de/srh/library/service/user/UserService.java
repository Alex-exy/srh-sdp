package de.srh.library.service.user;

import de.srh.library.entity.User;
import de.srh.library.dto.ApiResponse;

import java.util.Map;

public interface UserService {
    ApiResponse checkPassword(String inputUserName, String inputPassword);
    ApiResponse<Map<String, Integer>> getAllSchools();
    ApiResponse createUser(User user);
    ApiResponse<User> getUserByEmail(String email);
    ApiResponse<Integer> updateUserPassword(String password,String email);
}
