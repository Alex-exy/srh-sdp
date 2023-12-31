package de.srh.library.service.borrow;

import de.srh.library.dto.ApiResponse;
import de.srh.library.entity.Borrow;

import java.sql.Date;
import java.util.List;

public interface BorrowService {
    ApiResponse<List<Borrow>> getOngoingBorrows(Long userId);

    ApiResponse<Integer> countBorrowedBookByBookId(Long bookId);

    ApiResponse<Integer> insertBorrow(Borrow borrow);
    ApiResponse<Integer> userBorrowCount(long bookId);
    ApiResponse updateBorrowStatus(Long borrowId, char borrowStatus);

    ApiResponse updateExtensionsAndExpectedReturnDate(Long borrowId, int extensionCount, Date expectedReturnDate);

}
