package de.srh.library.dao;

import static org.junit.jupiter.api.Assertions.*;
import de.srh.library.entity.Book;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookDaoTest {

    private static BookDao bookDao;
    private static BorrowsDao borrowsDao;
    private static Book initBook;

    private static long bookId;

    @BeforeAll
    static void setup() {
        bookDao = new BookDao();
        borrowsDao = new BorrowsDao();

        initBook = new Book();
        initBook.setBookName("Test Book");
        initBook.setSubtitles("Subtitles");
        initBook.setLanguage("English");
        initBook.setIsbn("2412415");
        initBook.setPublishDate("22/02/22");
        initBook.setBookAuthor( UUID.randomUUID()+"");
        initBook.setPrice("25");
        initBook.setBookDescription("Fun");
        initBook.setLibraryId(2);
        initBook.setDoi("2515AA");

    }

    @Test
    @Order(1)
    void insertBook(){
        assertEquals(0, initBook.getBookId());
        assertEquals(1, bookDao.insertBook(initBook));
        assertTrue(initBook.getBookId() != 0);
    }
    @Test
    @Order(2)
    void getBookByAuthor() {
        assertEquals("25", bookDao.getBookByAuthor(initBook.getBookAuthor()).getPrice());
    }

    @Test
    @Order(3)
    void getBookById() {
        System.out.println(bookDao.getBookById(1L));

    }
    @Test
    @Order(4)
    void listOfBorrowedBooks(){
        System.out.println(borrowsDao.listOfBorrowedBooks('B'));
    }
}
