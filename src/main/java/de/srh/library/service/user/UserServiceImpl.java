package de.srh.library.service.user;

import de.srh.library.dao.SchoolDao;
import de.srh.library.dao.UserDao;
import de.srh.library.entity.dto.ApiResponse;
import de.srh.library.entity.dto.ApiResponseCode;
import de.srh.library.entity.User;
import de.srh.library.util.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;
    private final SchoolDao schoolDao;

    private UserServiceImpl(UserDao userDao,SchoolDao schoolDao) {
        this.userDao = userDao;
        this.schoolDao = schoolDao;
    }

    public static UserServiceImpl createInstance() {
        UserDao userDao = new UserDao();
        SchoolDao schoolDao = new SchoolDao();
        return new UserServiceImpl(userDao, schoolDao);
    }

    @Override
    public ApiResponse checkPassword(String username, String password) {
        try{
            User user = userDao.getUserByEmail(username);
            if (user == null){
                return ApiResponse.error(ApiResponseCode.ERROR_USER_NOT_EXIT);
            }
            if (PasswordUtils.checkPw(password, user.getPasswordHash())){
                return ApiResponse.success();
            }else {
                return ApiResponse.error(ApiResponseCode.ERROR_USER_PASSWORD_WRONG);
            }
        }catch (Exception e){
            logger.error("Checking password failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_UNKNOWN);
        }
    }

    @Override
    public ApiResponse getAllSchools() {
        try{
            return ApiResponse.success(schoolDao.getAllSchools());
        }catch (Exception e){
            logger.error("Querying school list failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

  @Override
  public ApiResponse createUser(User user) {
    try {
      userDao.insertUser(user);
      return ApiResponse.success();
    } catch (Exception e) {
      logger.error("Creating a new user failed.", e);
      return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
    }
  }
}
