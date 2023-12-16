package de.srh.library.mapper.books;

import de.srh.library.dto.BookDto;
import de.srh.library.entity.Book;


public interface BookMapper {
    BookDto getBookById(Long bookId);

    BookDto getBookByAuthor(String author);

    int insertBook(Book book);
    int removeBook(long bookId);
    int bookFound(long bookId);
    int updateBookInfo(Book book);
}
