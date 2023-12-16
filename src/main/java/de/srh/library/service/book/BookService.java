package de.srh.library.service.book;

import de.srh.library.dto.BookDto;
import de.srh.library.entity.Book;
import de.srh.library.dto.ApiResponse;
import java.util.Map;

public interface BookService {
    ApiResponse<Map<String, Integer>> getAllGenres();
    ApiResponse<Map<String, Integer>> getAllLibraries();
    ApiResponse<BookDto> getBookById(long bookId);
    ApiResponse<Integer> updateBookInfo(Book book);
    ApiResponse<Integer> insertBook(Book book);
    ApiResponse<BookDto> getBookByAuthor(String author);
    ApiResponse<Integer> removeBook(long bookId);
    ApiResponse<Integer> bookFound(long bookId);
    String bookGenreName(long bookId);
    String bookLibraryName(long bookId);


}
