package de.srh.library.service.user;

import de.srh.library.dto.UserDto;
import de.srh.library.entity.User;
import de.srh.library.dto.ApiResponse;

import java.util.Map;

public interface UserService {
    ApiResponse<Long> checkPassword(String inputUserName, String inputPassword);
    ApiResponse<Map<String, Integer>> getAllSchools();
    ApiResponse createUser(User user);
    ApiResponse<UserDto> getUserByEmail(String email);
    ApiResponse<Integer> updateUserPassword(String password,String email);
    ApiResponse<Integer> updateUserInfo(User user);
    ApiResponse<Integer> removeUser(long userId);
    ApiResponse<UserDto> getUserById(long userId);
    ApiResponse<Integer> userFoundId(long userId);
    ApiResponse<Integer> userFoundEmail(String email);
    Long getIdByEmail(String email);

}
