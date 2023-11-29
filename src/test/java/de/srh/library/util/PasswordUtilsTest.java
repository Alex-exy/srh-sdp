package de.srh.library.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PasswordUtilsTest {

  @Test
  void hashPw() {
    String password = "123456";
    String hashedPassword = PasswordUtils.hashPw(password);

    assertNotNull(hashedPassword);
    assertNotEquals(password, hashedPassword);
  }

  @Test
  void checkPw() {
    String password = "123456";
    String hashedPassword = PasswordUtils.hashPw(password);

    assertTrue(PasswordUtils.checkPw(password, hashedPassword));
    assertFalse(PasswordUtils.checkPw("1234567", hashedPassword));
  }
}
