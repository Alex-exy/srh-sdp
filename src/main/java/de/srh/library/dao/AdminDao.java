package de.srh.library.dao;

import de.srh.library.mapper.MyBatisSqlSessionFactory;
import de.srh.library.mapper.admins.AdminMapper;
import org.apache.ibatis.session.SqlSession;

public class AdminDao {
    public String getAdminPasswordHash(String adminUserName) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            AdminMapper mapper = session.getMapper(AdminMapper.class);
            return mapper.getAdminPasswordHash(adminUserName);
        }
    }
}
