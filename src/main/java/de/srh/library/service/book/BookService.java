package de.srh.library.service.book;

import de.srh.library.dto.BookDto;
import de.srh.library.dto.ApiResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookService {
    ApiResponse<Map<String, Integer>> getAllGenres();
    ApiResponse<Map<String, Integer>> getAllLibraries();
    ApiResponse<BookDto> getBookById(long bookId);

    ApiResponse<Map<Long,List<String>>> findBooks(@Param("bookName")String bookName, @Param("bookAuthor")String bookAuthor,
                                                  @Param("genreId")int genreId,
                                                  @Param("isbn")String isbn, @Param("doi")String doi,
                                                  @Param("bookId")long bookId, @Param("libraryId")int libraryId);
    ApiResponse<Integer> updateBookInfo(BookDto bookDto);
    ApiResponse<Integer> insertBook(BookDto bookDto);

    ApiResponse<Integer> removeBook(long bookId);
    ApiResponse<Integer> bookFound(BookDto bookDto);
    String bookGenreName(long bookId);
    String bookLibraryName(long bookId);


}
