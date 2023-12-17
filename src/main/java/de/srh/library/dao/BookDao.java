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

    public Map<Long,List<String>> getBookByAuthor(String bookAuthor) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            List<BookDto> authorBooks = mapper.getBookByAuthor(bookAuthor);
            Map<Long, List<String>> authorBookMap = new HashMap<>();
            for (BookDto bookDto : authorBooks) {
                long bookId = bookDto.getBookId();
                authorBookMap.put(bookDto.getBookId(), Arrays.asList(bookDto.getBookName(),
                        bookDto.getBookAuthor(),bookDto.getGenreName(bookId),
                        bookDto.getIsbn(),bookDto.getDoi(),bookDto.getLibraryName(bookId)));
            }
            return authorBookMap;
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
    public Map<Long,List<String>> getBookByIsbn(String isbn) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            List<BookDto> isbnBooks = mapper.getBookByIsbn(isbn);
            Map<Long, List<String>> isbnBookMap = new HashMap<>();
            for (BookDto bookDto : isbnBooks) {
                long bookId = bookDto.getBookId();
                isbnBookMap.put(bookDto.getBookId(), Arrays.asList(bookDto.getBookName(),
                        bookDto.getBookAuthor(),bookDto.getGenreName(bookId),
                        bookDto.getIsbn(),bookDto.getDoi(),bookDto.getLibraryName(bookId)));
            }return isbnBookMap;
        }
    }
    public Map<Long,List<String>> bookByGenre(int genreId) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            List<BookDto> bookByGenre = mapper.bookByGenre(genreId);
            Map<Long, List<String>> bookByGenreMap = new HashMap<>();
            for (BookDto bookDto : bookByGenre) {
                long bookId = bookDto.getBookId();
                bookByGenreMap.put(bookDto.getBookId(), Arrays.asList(bookDto.getBookName(),
                        bookDto.getBookAuthor(),bookDto.getGenreName(bookId),
                        bookDto.getIsbn(),bookDto.getDoi(),bookDto.getLibraryName(bookId)));
            }return bookByGenreMap;
        }
    }
    public Map<Long,List<String>> bookByLibrary(int libraryId) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            List<BookDto> bookByLibrary = mapper.bookByLibrary(libraryId);
            Map<Long, List<String>> bookByLibraryMap = new HashMap<>();
            for (BookDto bookDto : bookByLibrary) {
                long bookId = bookDto.getBookId();
                bookByLibraryMap.put(bookDto.getBookId(), Arrays.asList(bookDto.getBookName(),
                        bookDto.getBookAuthor(),bookDto.getGenreName(bookId),
                        bookDto.getIsbn(),bookDto.getDoi(),bookDto.getLibraryName(bookId)));
            }return bookByLibraryMap;
        }
    }
    public Map<Long,List<String>> getBookByName(String bookName) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            List<BookDto> nameBooks = mapper.getBookByName(bookName);
            Map<Long, List<String>> nameBooksMap = new HashMap<>();
            for (BookDto bookDto : nameBooks) {
                long bookId = bookDto.getBookId();
                nameBooksMap.put(bookDto.getBookId(), Arrays.asList(bookDto.getBookName(),
                        bookDto.getBookAuthor(),bookDto.getGenreName(bookId),
                        bookDto.getIsbn(),bookDto.getDoi(),bookDto.getLibraryName(bookId)));
            }return nameBooksMap;
        }
    }
    public BookDto getBookByDoi(String doi) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.getBookByDoi(doi);
        }
    }
}