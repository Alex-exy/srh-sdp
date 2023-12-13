package de.srh.library.mapper.borrows;

import de.srh.library.entity.Book;

import java.util.ArrayList;
import java.util.List;

public interface BorrowsMapper {
    List<Book> listOfBorrowedBooks(char borrowStatus);
}
