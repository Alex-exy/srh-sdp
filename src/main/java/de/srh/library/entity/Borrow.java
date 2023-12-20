package de.srh.library.entity;

import java.util.Date;

public class Borrow {
    private long borrowId;
    private long userId;
    private long bookId;
    private String bookName;
    private Date borrowDate;
    private Date returnDate;
    private char borrowStatus;
    private Date updateTime;

    private Date expectedReturnDate;

    private int extensions;

    public long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(long borrowId) {
        this.borrowId = borrowId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public char getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(char borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public int getExtensions() {
        return extensions;
    }

    public void setExtensions(int extensions) {
        this.extensions = extensions;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "borrowId=" + borrowId +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", bookName=" + bookName +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", borrowStatus=" + borrowStatus +
                ", updateTime=" + updateTime +
                ", expectedReturnDate=" + expectedReturnDate +
                ", extensions=" + extensions +
                '}';
    }

    public String toPaneMessage() {
        return "<html><b>Borrow</b><br>" +
                "<b>borrowId:</b> " + borrowId +
                "<br><b>userId:</b> " + userId +
                "<br><b>bookId:</b> " + bookId +
                "<br><b>bookName:</b> " + bookName +
                "<br><b>borrowDate:</b> " + borrowDate +
                "<br><b>returnDate:</b> " + returnDate +
                "<br><b>borrowStatus:</b> " + borrowStatus +
                "<br><b>updateTime:</b> " + updateTime +
                "<br><b>expectedReturnDate:</b> " + expectedReturnDate +
                "<br><b>extensions:</b> " + extensions +
                "</html>";
    }
}
