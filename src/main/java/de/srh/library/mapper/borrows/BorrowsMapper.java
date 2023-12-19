package de.srh.library.mapper.borrows;


import de.srh.library.entity.Borrow;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

public interface BorrowsMapper {
    List<Borrow> listOfBorrowedBooks(char borrowStatus);

    List<Borrow> getOngoingBorrows(Long userID);

    int insertBorrow(Borrow borrow);

    Borrow getByBookId(Long bookId);

    void updateBorrowStatus(@Param("borrowId") Long borrowId, @Param("borrowStatus")char borrowStatus);

    void updateExtensionsAndExpectedReturnDate(@Param("borrowId") Long borrowId,
                                               @Param("extensions")int extensions,
                                               @Param("expectedReturnDate")Date expectedReturnDate);
}
