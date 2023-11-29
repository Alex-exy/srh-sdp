package de.srh.library.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

  public static String hashPw(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public static boolean checkPw(String password, String hashedPassword) {
    return BCrypt.checkpw(password, hashedPassword);
  }
}
