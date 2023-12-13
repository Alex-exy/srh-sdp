package de.srh.library.mapper.books;

import de.srh.library.entity.Book;


public interface BookMapper {
    Book getBookById(Long bookId);

    Book getBookByAuthor(String author);

    int insertBook(Book book);
    int removeBook(Long bookId);
}
