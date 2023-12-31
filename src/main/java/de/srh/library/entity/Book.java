package de.srh.library.entity;

import java.util.Date;

public class Book {
    private long bookId;;
    private String bookName;
    private String subtitles;
    private String language;
    private String isbn;
    private String publishDate;
    private String bookAuthor;
    private int genreId;
    private String price;
    private String bookDescription;
    private int libraryId;
    private String doi;
    private Date additionDate;
    private Date updateDate;



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

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    @Override
    public String toString() {
        return
                "\n" +
                " Book ID = " + bookId + "\n" +
                " Book Name = '" + bookName + '\'' + "\n" +
                " Subtitles = '" + subtitles + '\'' + "\n" +
                " Language = '" + language + '\'' + "\n" +
                " ISBN = '" + isbn + '\'' + "\n" +
                " Publish Date = '" + publishDate + '\'' + "\n" +
                " Book Author = '" + bookAuthor + '\'' + "\n" +
                " Genre ID = '" + genreId + '\'' + "\n" +
                " Book Description = '" + bookDescription + '\'' + "\n" +
                " Addition Date = '" + additionDate + '\'' + "\n" +
                " Update Date '= " + updateDate + '\'' + "\n" +
                " Library ID = '" + libraryId + '\'' + "\n" +
                " DOI = '" + doi + '\'' +" ";
    }
}
