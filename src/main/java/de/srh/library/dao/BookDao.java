package de.srh.library.dao;
import de.srh.library.entity.Book;
import de.srh.library.mapper.MyBatisSqlSessionFactory;
import de.srh.library.mapper.books.BookMapper;
import org.apache.ibatis.session.SqlSession;

public class BookDao {
    public Book getBookById(Long bookId) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.getBookById(bookId);
        }
    }

    public int insertBook(Book book) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession(true)) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.insertBook(book);
        }
    }

    public Book getBookByAuthor(String author) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.getBookByAuthor(author);
        }
    }

    public int removeBook(Long bookId) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession(true)) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.removeBook(bookId);
        }
    }
    public int bookFound(Long bookId) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession(true)) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.bookFound(bookId);
        }
    }
    public int updateBookInfo(Book book) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession(true)) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.updateBookInfo(book);
        }
    }
}