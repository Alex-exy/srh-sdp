package de.srh.library.util;

import cn.hutool.core.lang.Validator;

import javax.swing.*;

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
    public static void validateFirstName(String firstName){
        Validator.validateMatchRegex("\\S+",firstName,"First Name field cannot be empty");
        Validator.validateMatchRegex(
                "[a-zA-Z]+", firstName, "First Name field should only contain letters");
    }
    public static void validateLastName(String lastName){
        Validator.validateMatchRegex("\\S+",lastName,"Last Name field cannot be empty");
        Validator.validateMatchRegex(
                "[a-zA-Z]+", lastName, "Last Name field should only contain letters");
    }

    public static void validateAddress(String address){
    Validator.validateMatchRegex(
        "^[\\p{L}0-9.,\\s-]{1,255}$", address, "Invalid address.");
    }
    public static void validateLanguage(String language){
        Validator.validateMatchRegex("\\S+",language,"Language field cannot be empty");
        Validator.validateMatchRegex(
                "[a-zA-Z]+", language, "Language field should only contain letters");
    }
    public static void validateAuthor(String author){
        Validator.validateMatchRegex("\\S+",author,"Author field cannot be empty");
        Validator.validateMatchRegex(
                "[a-zA-Z]+", author, "Author field should only contain letters");
    }
    public  static void validateIsbn(String isbn){
        Validator.validateMatchRegex("\\S+",isbn,"ISBN field cannot be empty");
        Validator.validateMatchRegex("^[0-9\\-]+",isbn,"ISBN field should only contain numbers and \"-\"");
    }
    public  static void validatePrice(String price){
        Validator.validateMatchRegex("\\S+",price,"Price field cannot be empty");
        Validator.validateMatchRegex("\\d+",price,"Price field should only contain numbers");
    }
    public  static void validateBookId(String bookId){
        Validator.validateMatchRegex("\\d+",bookId,"Book ID field should only contain numbers");
    }
    public  static void validateUserId(String userId){
        Validator.validateMatchRegex("\\d+",userId,"User ID field should only contain numbers");
    }
    public static void validateBookName(String bookName){
        Validator.validateMatchRegex("\\S+",bookName,"Book Title field cannot be empty");
    }
    public static void validateBookDescription(String description){
        Validator.validateMatchRegex("\\S+",description,"Book Description field cannot be empty");
    }
    public static void validateSubtitles(String subtitles){
        Validator.validateMatchRegex("\\S+",subtitles,"Subtitle field cannot be empty");
    }

    public static void validatePublishDate(String publishDate){
        Validator.validateMatchRegex("\\S+",publishDate,"Publish Date field cannot be empty");
        Validator.validateMatchRegex("^[0-9\\-]+",publishDate,"Please enter a date with format YYYY-MM-DD");
    }
    public static void validateDoi(String doi){
        Validator.validateMatchRegex("^[0-9\\-]*$|^$",doi,"DOI field should only contain numbers and \"-\"");

    }

    public static void validateVerificationCode(String code){
        Validator.validateMatchRegex(
                "\\d{4}", code, "Invalid verification code.");
    }
}
