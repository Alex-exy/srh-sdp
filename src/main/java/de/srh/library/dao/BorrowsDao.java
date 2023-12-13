package de.srh.library.dao;

import de.srh.library.entity.Book;
import de.srh.library.mapper.MyBatisSqlSessionFactory;
import de.srh.library.mapper.borrows.BorrowsMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BorrowsDao {
    public List<Book> listOfBorrowedBooks(char borrowStatus) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            BorrowsMapper mapper = session.getMapper(BorrowsMapper.class);
            return mapper.listOfBorrowedBooks(borrowStatus);
        }
    }
}