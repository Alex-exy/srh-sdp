package de.srh.library.dao;

import static org.junit.jupiter.api.Assertions.*;

import de.srh.library.entity.User;
import de.srh.library.util.PasswordUtils;
import java.util.UUID;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsersDaoTest {

  private static UserDao usersDao;
  private static User initUser;

  private static long userId;

  @BeforeAll
  static void setup() {
    usersDao = new UserDao();

    initUser = new User();
    initUser.setEmail(UUID.randomUUID() + "@test.com");
    initUser.setUserRole("S");
    initUser.setFirstName("Taylor");
    initUser.setFamilyName("Swift");
    initUser.setAddress("Heidelberg");
    initUser.setUserStatus("A");
    initUser.setPasswordHash(PasswordUtils.hashPw("000000"));
  }

  @Test
  @Order(1)
  void insertUser(){
    assertTrue(initUser.getUserId() == null);
    assertEquals(1, usersDao.insertUser(initUser));
    assertTrue(initUser.getUserId() != null);
  }
  @Test
  @Order(2)
  void getUserByEmail() {
    assertTrue(usersDao.getUserByEmail(initUser.getEmail()).getAddress().equals("Heidelberg"));
  }

}
