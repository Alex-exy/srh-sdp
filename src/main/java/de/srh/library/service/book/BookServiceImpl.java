package de.srh.library.service.book;

import de.srh.library.dao.BookDao;
import de.srh.library.dao.GenreDao;
import de.srh.library.dao.LibraryDao;
import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.ApiResponseCode;
import de.srh.library.dto.BookDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class BookServiceImpl implements BookService{
    Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final LibraryDao libraryDao;

    public BookServiceImpl(BookDao bookDao, GenreDao genreDao, LibraryDao libraryDao) {
        this.bookDao = bookDao;
        this.genreDao = genreDao;
        this.libraryDao = libraryDao;
    }

    public static BookServiceImpl createInstance(){
        BookDao bookDao = new BookDao();
        GenreDao genreDao = new GenreDao();
        LibraryDao libraryDao = new LibraryDao();
        return new BookServiceImpl(bookDao,genreDao,libraryDao);
    }

    @Override
    public ApiResponse<Map<String, Integer>> getAllGenres() {
        try{
            return ApiResponse.success(genreDao.getAllGenres());
        }catch (Exception e){
            logger.error("Querying genre list failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

    @Override
    public ApiResponse<Map<String, Integer>> getAllLibraries() {
        try{
            return ApiResponse.success(libraryDao.getAllLibraries());
        }catch (Exception e){
            logger.error("Querying library list failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

    @Override
    public ApiResponse<BookDto> getBookById(long bookId) {
        try{
            return ApiResponse.success(bookDao.getBookById(bookId));
        }catch (Exception e){
            logger.error("Querying Book ID failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }



    @Override
    public ApiResponse<Map<Long, List<String>>> findBooks(String bookName, String bookAuthor,int genreId, String isbn,
                                                          String doi,long bookId,int libraryId)
    {
        try{
            return ApiResponse.success(bookDao.findBooks(bookName,bookAuthor,genreId,isbn,doi,bookId,libraryId));
        }catch (Exception e){
            logger.error("Querying Book failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }


    @Override
    public ApiResponse<Integer> updateBookInfo(BookDto bookDto) {
        try{
            return ApiResponse.success(bookDao.updateBookInfo(bookDto));
        }catch (Exception e){
            logger.error("Book Information update failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

    @Override
    public ApiResponse<Integer> insertBook(BookDto bookDto) {
        try{
            return ApiResponse.success(bookDao.insertBook(bookDto));
        }catch (Exception e){
            logger.error("Book Insertion failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }



    @Override
    public ApiResponse<Integer> removeBook(long bookId) {
        try{
            return ApiResponse.success(bookDao.removeBook(bookId));
        }catch (Exception e){
            logger.error("Book removal failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

    @Override
    public ApiResponse<Integer> bookFound(BookDto bookDto) {
        try{
            return ApiResponse.success(bookDao.bookFound(bookDto));
        }catch (Exception e){
            logger.error("Book search failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

    @Override
    public String bookGenreName(long bookId) {
        try{
            return bookDao.bookGenreName(bookId);
        }catch (Exception e){
            logger.error("Book genre name retrieval failed", e);
            return"Book genre name retrieval failed";
        }
    }

    @Override
    public String bookLibraryName(long bookId) {
        try{
            return bookDao.bookLibraryName(bookId);
        }catch (Exception e){
            logger.error("Book library name retrieval failed", e);
            return"Book library name retrieval failed";
        }
    }
}
