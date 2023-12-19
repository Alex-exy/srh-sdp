package de.srh.library.dao;

import de.srh.library.entity.Borrow;
import de.srh.library.mapper.MyBatisSqlSessionFactory;
import de.srh.library.mapper.borrows.BorrowsMapper;
import org.apache.ibatis.session.SqlSession;

import java.sql.Date;
import java.util.List;

public class BorrowsDao {

    public List<Borrow> getOngoingBorrows(Long userID) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            BorrowsMapper mapper = session.getMapper(BorrowsMapper.class);
            return mapper.getOngoingBorrows(userID);
        }
    }

    public List<Borrow> listOfBorrowedBooks(char borrowStatus) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            BorrowsMapper mapper = session.getMapper(BorrowsMapper.class);
            return mapper.listOfBorrowedBooks(borrowStatus);
        }
    }

    public int insertBorrow(Borrow borrow) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession(true)) {
            BorrowsMapper mapper = session.getMapper(BorrowsMapper.class);
            return mapper.insertBorrow(borrow);
        }
    }

    public Borrow getByBookId(Long bookId) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            BorrowsMapper mapper = session.getMapper(BorrowsMapper.class);
            return mapper.getByBookId(bookId);
        }
    }

    public void updateBorrowStatus(Long borrowId, char borrowStatus) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession(true)) {
            BorrowsMapper mapper = session.getMapper(BorrowsMapper.class);
            mapper.updateBorrowStatus(borrowId, borrowStatus);
        }
    }

    public void updateExtensionsAndExpectedReturnDate(Long borrowId, int extensionCount, Date expectedReturnDate) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession(true)) {
            BorrowsMapper mapper = session.getMapper(BorrowsMapper.class);
            mapper.updateExtensionsAndExpectedReturnDate(borrowId, extensionCount, expectedReturnDate);
        }
    }
}