package de.srh.library.service.user;

import de.srh.library.entity.User;
import de.srh.library.dto.ApiResponse;

public interface UserService {
    ApiResponse checkPassword(String inputUserName, String inputPassword);
    ApiResponse getAllSchools();
    ApiResponse createUser(User user);
}
