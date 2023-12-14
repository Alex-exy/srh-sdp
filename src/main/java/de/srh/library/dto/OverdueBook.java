package de.srh.library.dto;

public class OverdueBook {
  private String bookName;
  private int daysOverdue;
  private String fineAmount;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getDaysOverdue() {
        return daysOverdue;
    }

    public void setDaysOverdue(int daysOverdue) {
        this.daysOverdue = daysOverdue;
    }

    public String getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(String fineAmount) {
        this.fineAmount = fineAmount;
    }

    public OverdueBook(String bookName, int daysOverdue, String fineAmount) {
    this.bookName = bookName;
    this.daysOverdue = daysOverdue;
    this.fineAmount = fineAmount;
  }
}
