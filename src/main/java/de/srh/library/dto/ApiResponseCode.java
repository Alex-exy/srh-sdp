package de.srh.library.dto;

public enum ApiResponseCode {
  SUCCESS(0, "Success"),
  ERROR_USER_NOT_EXIT(1, "Email has not been registered"),
  ERROR_USER_PASSWORD_WRONG(2, "Password is wrong"),

  ERROR_UNKNOWN(9999, "Unknown error");


  private final int code;
  private final String message;

  ApiResponseCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public static ApiResponseCode getByCode(int code) {
    for (ApiResponseCode enumConstant : ApiResponseCode.values()) {
      if (enumConstant.code == code) {
        return enumConstant;
      }
    }
    throw new IllegalArgumentException("No matching constant for code: " + code);
  }
}
