package de.srh.library.service.user;

import de.srh.library.dao.SchoolDao;
import de.srh.library.dao.UserDao;
import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.ApiResponseCode;
import de.srh.library.dto.UserDto;
import de.srh.library.entity.User;
import de.srh.library.util.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

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
    public ApiResponse<Long> checkPassword(String username, String password) {
        try{
            UserDto user = userDao.getUserByEmail(username);
            if (user == null){
                return ApiResponse.error(ApiResponseCode.ERROR_USER_NOT_EXIT);
            }
            if (PasswordUtils.checkPw(password, user.getPasswordHash())){
                return ApiResponse.success(user.getUserId());
            }else {
                return ApiResponse.error(ApiResponseCode.ERROR_USER_PASSWORD_WRONG);
            }
        }catch (Exception e){
            logger.error("Checking password failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_UNKNOWN);
        }
    }

    @Override
    public ApiResponse<Map<String, Integer>> getAllSchools() {
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

    @Override
    public ApiResponse<UserDto> getUserByEmail(String email) {
        try{
            return ApiResponse.success(userDao.getUserByEmail(email));
        }catch (Exception e){
            logger.error("Query user by email failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

    @Override
    public ApiResponse<Integer> updateUserPassword(String password,String email) {
        try{
            userDao.updateUserPassword(password,email);
            return ApiResponse.success();
        }catch (Exception e){
            logger.error("Password update failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

    @Override
    public ApiResponse updateUserInfo(User user) {
        try{
            userDao.updateUserInfo(user);
            return ApiResponse.success();
        }catch (Exception e){
            logger.error("User information update failed!", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

    @Override
    public ApiResponse<Integer> removeUser(long userId) {
        try{
            userDao.removeUser(userId);
            return ApiResponse.success();
        }catch (Exception e){
            logger.error("User removal failed!", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

    @Override
    public ApiResponse<UserDto> getUserById(long userId) {
        try{
            return ApiResponse.success(userDao.getUserById(userId));
        }catch (Exception e){
            logger.error("Query user by ID failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

    @Override
    public ApiResponse<Integer> userFoundId(long userId) {
        try{
            return ApiResponse.success(userDao.userFoundId(userId));
        }catch (Exception e){
            logger.error("User search failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

    @Override
    public ApiResponse<Integer> userFoundEmail(String email) {
        try{
            return ApiResponse.success(userDao.userFoundEmail(email));
        }catch (Exception e){
            logger.error("User search failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }



    @Override
    public Long getIdByEmail(String email) {
        try{
            return ApiResponse.success(userDao.getIdByEmail(email)).getData();
        }catch (Exception e){
            logger.error("Error getting Id.", e);
            return 0L;
        }
    }


}
