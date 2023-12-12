package de.srh.library.service.admin;

import de.srh.library.dto.ApiResponse;

public interface AdminService {
    ApiResponse checkPassword(String adminUserName, String adminPassword);
}
