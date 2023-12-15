package de.srh.library.service.book;

import de.srh.library.dao.BookDao;
import de.srh.library.dao.GenreDao;
import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.ApiResponseCode;
import de.srh.library.dto.BookDto;
import de.srh.library.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class BookServiceImpl implements BookService{
    Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    private final BookDao bookDao;
    private final GenreDao genreDao;

    public BookServiceImpl(BookDao bookDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.genreDao = genreDao;
    }

    public static BookServiceImpl createInstance(){
        BookDao bookDao = new BookDao();
        GenreDao genreDao = new GenreDao();
        return new BookServiceImpl(bookDao,genreDao);
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
    public ApiResponse<BookDto> getBookById(long bookId) {
        try{
            return ApiResponse.success(bookDao.getBookById(bookId));
        }catch (Exception e){
            logger.error("Querying Book ID failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

    @Override
    public ApiResponse<Integer> updateBookInfo(Book book) {
        try{
            return ApiResponse.success(bookDao.updateBookInfo(book));
        }catch (Exception e){
            logger.error("Book Information update failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

    @Override
    public ApiResponse<Integer> insertBook(Book book) {
        try{
            return ApiResponse.success(bookDao.insertBook(book));
        }catch (Exception e){
            logger.error("Book Insertion failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

    @Override
    public ApiResponse<BookDto> getBookByAuthor(String author) {
        try{
            return ApiResponse.success(bookDao.getBookByAuthor(author));
        }catch (Exception e){
            logger.error("Book Information update failed.", e);
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
    public ApiResponse<Integer> bookFound(long bookId) {
        try{
            return ApiResponse.success(bookDao.bookFound(bookId));
        }catch (Exception e){
            logger.error("Book search failed.", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }
}
