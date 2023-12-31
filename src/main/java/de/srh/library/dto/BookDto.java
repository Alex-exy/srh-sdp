package de.srh.library.dto;

import de.srh.library.service.book.BookService;
import de.srh.library.service.book.BookServiceImpl;

import java.util.Date;

public class BookDto {
    private long bookId;;
    private String borrowStatus;
    private long borrowId;
    private String bookName;
    private String subtitles;
    private String language;
    private String isbn;
    private String publishDate;
    private String bookAuthor;
    private int genreId;

    private String genreName;

    private String price;
    private String bookDescription;
    private int libraryId;
    private String libraryName;
    private String doi;
    private Date additionDate;
    private Date updateDate;
    private BookService bookService;

    public String getFine(int overdueDays) {
        Double price = Double.parseDouble(this.price);
        return String.valueOf(overdueDays * 0.5 > price * 2 ? price * 2 : overdueDays * 0.5);
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(String subtitles) {
        this.subtitles = subtitles;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(String borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(long borrowId) {
        this.borrowId = borrowId;
    }

    public String getGenreName() {
        return genreName;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public BookService getBookService() {
        return bookService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenreName(long bookId) {
        bookService = BookServiceImpl.createInstance();
        return bookService.bookGenreName(bookId);
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getPrice() {
        return (price);
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public Date getAdditionDate() {
        return additionDate;
    }

    public void setAdditionDate(Date additionDate) {
        this.additionDate = additionDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public String getLibraryName(long bookId) {
        bookService = BookServiceImpl.createInstance();
        return bookService.bookLibraryName(bookId);
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }


}
