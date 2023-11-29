package de.srh.library.dao;

import static org.junit.jupiter.api.Assertions.*;

import de.srh.library.entity.User;
import de.srh.library.util.PasswordUtils;
import java.util.UUID;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsersDAOTest {

  private static UsersDAO usersDAO;
  private static User initUser;

  private static long userId;

  @BeforeAll
  static void setup() {
    usersDAO = new UsersDAO();

    initUser = new User();

    userId = System.currentTimeMillis();

    initUser.setUserId(userId);
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
  void insertUser() throws Exception {
    assertEquals(1, usersDAO.insertUser(initUser));
  }

  @Test
  @Order(2)
  void getUserByUserId() throws Exception {
    User user = usersDAO.getUserByUserId(userId);
    assertTrue(user.getEmail().endsWith("@test.com"));
    assertEquals("S", user.getUserRole());
    assertEquals("Taylor", user.getFirstName());
    assertEquals("Swift", user.getFamilyName());
    assertEquals("Heidelberg", user.getAddress());
    assertEquals("A", user.getUserStatus());
  }

  @Test
  @Order(Integer.MAX_VALUE - 1)
  void updateUser() throws Exception {
    initUser.setAddress("Berlin");
    assertEquals(1, usersDAO.updateUser(userId, initUser));
    assertEquals("Berlin", usersDAO.getUserByUserId(userId).getAddress());
  }

  @Test
  @Order(Integer.MAX_VALUE)
  void deleteUser() throws Exception {
    assertEquals(1, usersDAO.deleteUser(userId));
    assertNull(usersDAO.getUserByUserId(userId));
  }
}
