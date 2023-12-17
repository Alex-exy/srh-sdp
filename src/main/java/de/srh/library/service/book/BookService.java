package de.srh.library.service.book;

import de.srh.library.dto.BookDto;
import de.srh.library.dto.ApiResponse;
import java.util.List;
import java.util.Map;

public interface BookService {
    ApiResponse<Map<String, Integer>> getAllGenres();
    ApiResponse<Map<String, Integer>> getAllLibraries();
    ApiResponse<BookDto> getBookById(long bookId);
    ApiResponse<Map<Long,List<String>>> getBookByIsbn(String isbn);
    ApiResponse<Map<Long,List<String>>> getBookByName(String bookName);
    ApiResponse<Map<Long,List<String>>> bookByGenre(int genreId);
    ApiResponse<Map<Long,List<String>>> bookByLibrary(int genreId);
    ApiResponse<BookDto> getBookByDoi(String doi);
    ApiResponse<Integer> updateBookInfo(BookDto bookDto);
    ApiResponse<Integer> insertBook(BookDto bookDto);
    ApiResponse<Map<Long, List<String>>> getBookByAuthor(String bookAuthor);
    ApiResponse<Integer> removeBook(long bookId);
    ApiResponse<Integer> bookFound(BookDto bookDto);
    String bookGenreName(long bookId);
    String bookLibraryName(long bookId);


}
