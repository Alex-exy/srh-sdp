package de.srh.library.service.admin;

import de.srh.library.dao.AdminDao;
import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.ApiResponseCode;
import de.srh.library.util.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminServiceImpl implements AdminService{

    Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    private final AdminDao adminDao;

    public AdminServiceImpl(AdminDao adminDao) {
        this.adminDao = adminDao;
    }
    public static AdminServiceImpl createInstance() {
        AdminDao admindao = new AdminDao();
        return new AdminServiceImpl(admindao);
    }

    @Override
    public ApiResponse checkPassword(String adminUserName, String adminPassword) {
        try{
            String adminPasswordHash = adminDao.getAdminPasswordHash(adminUserName);
            if (PasswordUtils.checkPw(adminPassword, adminPasswordHash)){
                return ApiResponse.success();
            }else{
                return ApiResponse.error(ApiResponseCode.ERROR_ADMIN_PASSWORD_WRONG);
            }
        }catch (Exception e){
            logger.error("Get admin password hash failed.");
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }
}
