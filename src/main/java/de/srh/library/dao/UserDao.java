package de.srh.library.dao;


import de.srh.library.entity.User;
import de.srh.library.mapper.MyBatisSqlSessionFactory;
import de.srh.library.mapper.users.UserMapper;
import org.apache.ibatis.session.SqlSession;

public class UserDao {

    public User getUserById(Long userId) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getUserById(userId);
        }
    }
    public Long getIdByEmail(String email){
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getIdByEmail(email);
        }
    }
  public User getUserByEmail(String email){
      try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
        UserMapper mapper = session.getMapper(UserMapper.class);
        return mapper.getUserByEmail(email);
      }
  }
    public void updateUserPassword(String password, String email ){
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.updateUserPassword(password,email);
        }
    }
    public int insertUser(User user){
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession(true)) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.insertUser(user);
        }
    }

    public void updateUserInfo(String email, String address, long userId){
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession(true)) {
            UserMapper mapper = session.getMapper(UserMapper.class);
             mapper.updateUserInfo(email,address,userId);
        }
    }
}
