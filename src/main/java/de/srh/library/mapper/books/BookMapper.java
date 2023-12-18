package de.srh.library.mapper.books;

import de.srh.library.dto.BookDto;
import java.util.List;


public interface BookMapper {
    BookDto getBookById(long bookId);
    List<BookDto> getBookByIsbn(String isbn);
    List<BookDto> getBookByName(String bookName);

    List<BookDto> getBookByDoi(String doi);

    List<BookDto> getBookByAuthor(String bookAuthor);
    List<BookDto> bookByGenre(int genreId);
    List<BookDto> bookByLibrary(int libraryId);

    int insertBook(BookDto bookDto);
    int removeBook(long bookId);
    int bookFound(BookDto bookDto);
    int updateBookInfo(BookDto bookDto);
    String bookGenreName(long bookId);
    String bookLibraryName(long bookId);

}
