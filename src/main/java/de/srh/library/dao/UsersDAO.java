package de.srh.library.dao;

import de.srh.library.entity.User;
import java.sql.SQLException;
import java.util.List;

public class UsersDAO {
  private static final String TABLE_NAME = "users";
  private final BaseDAO baseDAO;

  public UsersDAO() {
    this.baseDAO = new BaseDAO();
  }

  public int insertUser(User user) throws SQLException {
    String[] columns = {
      "user_id",
      "email",
      "user_role",
      "first_name",
      "family_name",
      "address",
      "user_status",
      "password_hash"
    };
    Object[] values = {
      user.getUserId(),
      user.getEmail(),
      user.getUserRole(),
      user.getFirstName(),
      user.getFamilyName(),
      user.getAddress(),
      user.getUserStatus(),
      user.getPasswordHash()
    };
    return baseDAO.insertOne(TABLE_NAME, columns, values);
  }

  public int updateUser(long userId, User user) throws SQLException {
    String[] columns = {
      "email", "user_role", "first_name", "family_name", "address", "user_status", "password_hash"
    };
    Object[] values = {
      user.getEmail(),
      user.getUserRole(),
      user.getFirstName(),
      user.getFamilyName(),
      user.getAddress(),
      user.getUserStatus(),
      user.getPasswordHash()
    };
    String conditionColumn = "user_id";
    Object conditionValue = userId;
    return baseDAO.update(TABLE_NAME, columns, values, conditionColumn, conditionValue);
  }

  public int deleteUser(Long userId) throws SQLException {
    String conditionColumn = "user_id";
    return baseDAO.delete(TABLE_NAME, conditionColumn, userId);
  }

  public User getUserByUserId(Long userId) throws SQLException {
    List<Object[]> resultList = baseDAO.select(TABLE_NAME, new String[] {}, "user_id", userId);
    if (resultList.size() > 0) {
      Object[] object = resultList.get(0);
      User user = new User();
      user.setUserId(userId);
      user.setEmail(String.valueOf(object[1]));
      user.setUserRole(String.valueOf(object[2]));
      user.setFirstName(String.valueOf(object[3]));
      user.setFamilyName(String.valueOf(object[4]));
      user.setAddress(String.valueOf(object[5]));
      user.setUserStatus(String.valueOf(object[6]));
      user.setPasswordHash(String.valueOf(object[7]));
      return user;
    }
    return null;
  }
}
