package de.srh.library.entity;

import java.util.Date;

public class Borrows {
    private long borrowId;
    private long userId;
    private long bookId;
    private Date borrowDate;
    private Date returnDate;
    private char borrowStatus;
    private Date updateTime;

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

    @Override
    public String toString() {
        return "Borrows{" +
                "borrowId=" + borrowId +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", borrowStatus=" + borrowStatus +
                ", updateTime=" + updateTime +
                '}';
    }
}
