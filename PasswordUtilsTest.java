package de.srh.library.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PasswordUtilsTest {
  private static final String TEST_ONE = "Valid short username";
  private static final String TEST_TWO = "Valid long username";
  private static final String TEST_THREE = "Invalid short username";
  private static final String TEST_FOUR = "Invalid long username";
  private static final String TEST_FIVE = "Empty username";
  private static final String TEST_SIX = "Null username";

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

  @Test
  public void testValidNames() {
    if(PasswordUtils.validateUserName("mike.muller@")) testLogger(TEST_ONE, "SUCCESSFUL");
    else testLogger(TEST_ONE, "FAILED");

    if(PasswordUtils.validateUserName("mike.muller@stud.hochschule-heidelberg.de")) testLogger(TEST_TWO, "SUCCESSFUL");
    else testLogger(TEST_TWO, "FAILED");
  }

  /**
   * Success only if false (invalid) is returned
   */
  @Test
  public void testInvalidNames() {
    if(!PasswordUtils.validateUserName("mike@y")) testLogger(TEST_THREE, "SUCCESSFUL");
    else testLogger(TEST_THREE, "FAILED");

    if(!PasswordUtils.validateUserName("mikemuller12#456")) testLogger(TEST_FOUR, "SUCCESSFUL");
    else testLogger(TEST_FOUR, "FAILED");

    if(!PasswordUtils.validateUserName("")) testLogger(TEST_FIVE, "SUCCESSFUL");
    else testLogger(TEST_FIVE, "FAILED");
  }

  private static void testLogger(String testName, String result) {
    System.out.println("Test " + testName + result);
  }
}