package de.srh.library.dao;

import de.srh.library.entity.Borrows;
import de.srh.library.mapper.MyBatisSqlSessionFactory;
import de.srh.library.mapper.borrows.BorrowsMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BorrowsDao {
    public List<Borrows> listOfBorrowedBooks(char borrowStatus) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            BorrowsMapper mapper = session.getMapper(BorrowsMapper.class);
            return mapper.listOfBorrowedBooks(borrowStatus);
        }
    }
}