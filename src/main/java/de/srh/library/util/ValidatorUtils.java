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
    public static void validateUsername(String username){
        Validator.validateMatchRegex("^[\\p{L}0-9@.]{1,150}$",username,"Username field can not be empty");
    }
    public static void validateFirstName(String firstName){
        Validator.validateMatchRegex("^[\\p{L}]{1,60}$",firstName,"First Name field cannot be empty and should only contain alphabets");
    }
    public static void validateLastName(String lastName){
        Validator.validateMatchRegex("^[\\p{L}]{1,60}$",lastName,"Last Name field cannot be empty and should only contain alphabets");
    }

    public static void validateAddress(String address){
    Validator.validateMatchRegex("^[\\p{L}0-9.,\\s-]{1,255}$", address, "Invalid address.");
    }
    public static void validateLanguage(String language){
        Validator.validateMatchRegex("^[\\p{L}]{1,60}$",language,"Language field cannot be empty and should only contain alphabets");

    }
    public static void validateAuthor(String author){
        Validator.validateMatchRegex("^[\\p{L}\\s-]{1,255}$",author,"Author field cannot be empty and should only contain alphabets");
    }
    public  static void validateIsbn(String isbn){
        Validator.validateMatchRegex("^[0-9-]{1,100}$",isbn,"ISBN field cannot be empty and should only contain numbers and \"-\"");
    }
    public  static void validatePrice(String price){
        Validator.validateMatchRegex("^[0-9.]{1,40}$",price,"Price field cannot be empty and should only contain numbers");
    }
    public  static void validateBookId(String bookId){
        Validator.validateMatchRegex("^[0-9]{1,15}$",bookId,"Book ID field should only contain numbers");
    }
    public  static void validateUserId(String userId){
        Validator.validateMatchRegex("^[0-9]{1,15}$",userId,"User ID field should only contain numbers");
    }
    public static void validateBookName(String bookName){
        Validator.validateMatchRegex("^[\\p{L}0-9.,\\s-]{1,200}$",bookName,"Book Title field cannot be empty");
    }
    public static void validateBookDescription(String description){
        Validator.validateMatchRegex("^[\\p{L}0-9.,\\s-]{1,300}$",description,"Book Description field cannot be empty");
    }
    public static void validateSubtitles(String subtitles){
        Validator.validateMatchRegex("^[\\p{L}0.,\\s]{1,200}",subtitles,"Subtitle field cannot be empty and should only contain alphabets");
    }

    public static void validatePublishDate(String publishDate){
        Validator.validateMatchRegex("^[0-9\\-]{1,20}$",publishDate,"Please enter a date with format YYYY-MM-DD");
    }
    public static void validateDoi(String doi){
        Validator.validateMatchRegex("^[0-9\\-]*$|^$",doi,"DOI field should only contain numbers and \"-\"");

    }

    public static void validateVerificationCode(String code){
        Validator.validateMatchRegex(
                "\\d{4}", code, "Invalid verification code.");
    }
}
