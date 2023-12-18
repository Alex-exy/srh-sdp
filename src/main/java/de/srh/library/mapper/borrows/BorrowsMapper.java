package de.srh.library.mapper.borrows;


import de.srh.library.entity.Borrows;

import java.util.ArrayList;
import java.util.List;

public interface BorrowsMapper {
    List<Borrows> listOfBorrowedBooks(char borrowStatus);
}
