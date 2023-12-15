package de.srh.library.dao;
import de.srh.library.dto.BookDto;
import de.srh.library.entity.Book;
import de.srh.library.mapper.MyBatisSqlSessionFactory;
import de.srh.library.mapper.books.BookMapper;
import org.apache.ibatis.session.SqlSession;

public class BookDao {
    public BookDto getBookById(long bookId) {
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

    public BookDto getBookByAuthor(String author) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.getBookByAuthor(author);
        }
    }

    public int removeBook(long bookId) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession(true)) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.removeBook(bookId);
        }
    }
    public int bookFound(long bookId) {
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