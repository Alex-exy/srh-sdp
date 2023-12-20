package de.srh.library.mapper.books;

import de.srh.library.dto.BookDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BookMapper {
    BookDto getBookById(long bookId);

    List<BookDto> findBooks(@Param("bookName")String bookName, @Param("bookAuthor")String bookAuthor,
                            @Param("genreId")int genreId,
                            @Param("isbn")String isbn, @Param("doi")String doi,
                            @Param("bookId")long bookId, @Param("libraryId")int libraryId);


    int insertBook(BookDto bookDto);
    int removeBook(long bookId);
    int bookFound(BookDto bookDto);
    int updateBookInfo(BookDto bookDto);
    String bookGenreName(long bookId);
    String bookLibraryName(long bookId);

}
