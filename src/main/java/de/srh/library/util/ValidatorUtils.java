package de.srh.library.util;

import cn.hutool.core.lang.Validator;

public class ValidatorUtils {

    public static void validateEmail(String email){
        Validator.validateEmail(email, "Invalid email address.");
    }
    public static void validatePassword(String firstInputPassword){
        Validator.validateMatchRegex("^[a-zA-Z0-9_]{6,20}$", firstInputPassword,
                "The password can only contain lowercase letters, uppercase letters, numbers, and underscores," +
                        " and its length must be between 6 and 20 characters.");
    }
    public static void validatePassword(String firstInputPassword, String secondInputPassword){
        validatePassword(firstInputPassword);
        Validator.validateTrue(firstInputPassword.equals(secondInputPassword), "Please input the same password.");
    }

    public static void validateName(String name){
    Validator.validateMatchRegex(
        "^.{1,50}$", name, "The length of name must be between 6 and 50 characters.");
    }

    public static void validateAddress(String address){
    Validator.validateMatchRegex(
        "^[\\p{L}0-9.,\\s-]{1,255}$", address, "Invalid address.");
    }

    public static void validateVerificationCode(String code){
        Validator.validateMatchRegex(
                "\\d{4}", code, "Invalid verification code.");
    }
}
