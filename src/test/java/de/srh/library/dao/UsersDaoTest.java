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
    initUser.setSchoolId(1001);
    initUser.setPasswordHash(PasswordUtils.hashPw("000000"));
  }

  @Test
  @Order(1)
  void insertUser(){
      assertNull(initUser.getUserId());
      assertEquals(1, usersDao.insertUser(initUser));
      assertNotNull(initUser.getUserId());
  }
  @Test
  @Order(2)
  void getUserByEmail() {
    System.out.println(usersDao.getUserByEmail("jack.muller@stud.hochschule-heidelberg.de"));
  }

  @Test
  @Order(3)
  void getUserById(){
    System.out.println(usersDao.getUserById(1000L));
  }
  @Test
  @Order(4)
  void getIdByEmail(){
    System.out.println(usersDao.getIdByEmail("jack.muller@stud.hochschule-heidelberg.de"));
  }

}
