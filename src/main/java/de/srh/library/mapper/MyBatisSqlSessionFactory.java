package de.srh.library.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
public class MyBatisSqlSessionFactory {

    private static final Logger logger = LoggerFactory.getLogger(MyBatisSqlSessionFactory.class);
    private MyBatisSqlSessionFactory() {
    }
    private static SqlSessionFactory sqlSessionFactory;
    static {
        try {
            if (sqlSessionFactory == null) {
                InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            }
        } catch (Exception e) {
            logger.error("Mybatis SqlSessionFactory initialization failed.", e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

}
