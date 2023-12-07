package de.srh.library.service.login;

import de.srh.library.dto.ApiResponse;

public interface Login {
    ApiResponse checkPassword(String inputUserName, String inputPassword);
}
