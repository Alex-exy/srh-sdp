package de.srh.library.util;

import org.mindrot.jbcrypt.BCrypt;
import java.util.regex.Pattern;

public class PasswordUtils {

  public static String hashPw(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public static boolean checkPw(String password, String hashedPassword) {
    return BCrypt.checkpw(password, hashedPassword);
  }

  public static boolean validateUserName(String name) {
    if (name == null) return false;
    if (name.length() < 6 || name.length() > 12) return false;
    // initializes our regex pattern, gives it the username, and returns true or false if they match
    return Pattern.compile(regex).matcher(name).matches();
  }
  private static final String regex = "^[a-zA-Z0-9]+$";

}
