package de.srh.library.dao;
import de.srh.library.dto.BookDto;
import de.srh.library.mapper.MyBatisSqlSessionFactory;
import de.srh.library.mapper.books.BookMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDao {
    public BookDto getBookById(long bookId) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.getBookById(bookId);
        }
    }

    public int insertBook(BookDto bookDto) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession(true)) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.insertBook(bookDto);
        }
    }

    public int removeBook(long bookId) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession(true)) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.removeBook(bookId);
        }
    }
    public int bookFound(BookDto bookDto) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession(true)) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.bookFound(bookDto);
        }
    }
    public int updateBookInfo(BookDto bookDto) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession(true)) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.updateBookInfo(bookDto);
        }
    }
    public String bookGenreName(long bookId){
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession(true)) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.bookGenreName(bookId);
        }
    }
    public String bookLibraryName(long bookId){
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession(true)) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.bookLibraryName(bookId);
        }
    }

    public Map<Long,List<String>> findBooks(String bookName, String bookAuthor,int genreId,
                                            String isbn,String doi,long bookId,int libraryId)
    {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            List<BookDto> findBooks = mapper.findBooks(bookName,bookAuthor,genreId,isbn,doi,bookId,libraryId);
            Map<Long, List<String>> findBooksMap = new HashMap<>();
            for (BookDto bookDto : findBooks) {
                long bookIdTemp = bookDto.getBookId();
                findBooksMap.put(bookDto.getBookId(), Arrays.asList(bookDto.getBookName(),
                        bookDto.getBookAuthor(),bookDto.getGenreName(bookIdTemp),
                        bookDto.getIsbn(),bookDto.getDoi(),bookDto.getLibraryName(bookIdTemp)));
            }return findBooksMap;
        }
    }
}