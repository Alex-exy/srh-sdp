package de.srh.library.service.login;

import de.srh.library.dao.UserDao;
import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.ApiResponseCode;
import de.srh.library.entity.User;
import de.srh.library.util.PasswordUtils;

public class LoginImpl implements Login {

    private final UserDao userDao;

    private LoginImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public static LoginImpl createInstance() {
        UserDao userDao = new UserDao();
        return new LoginImpl(userDao);
    }

    @Override
    public ApiResponse checkPassword(String username, String password) {
        User user = userDao.getUserByEmail(username);
        if (user == null){
            return ApiResponse.error(ApiResponseCode.ERROR_USER_NOT_EXIT);
        }
        if (PasswordUtils.checkPw(password, user.getPasswordHash())){
            return ApiResponse.success();
        }else {
            return ApiResponse.error(ApiResponseCode.ERROR_USER_PASSWORD_WRONG);
        }
    }
}
