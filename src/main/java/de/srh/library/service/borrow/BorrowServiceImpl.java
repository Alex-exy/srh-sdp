package de.srh.library.service.borrow;

import de.srh.library.dao.BorrowsDao;
import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.ApiResponseCode;
import de.srh.library.entity.Borrow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;

public class BorrowServiceImpl implements BorrowService{

    private Logger logger = LoggerFactory.getLogger(BorrowServiceImpl.class);
    private BorrowsDao borrowsDao;

    public BorrowServiceImpl(BorrowsDao borrowsDao) {
        this.borrowsDao = borrowsDao;
    }

    public static BorrowServiceImpl createInstance() {
        BorrowsDao borrowsDao = new BorrowsDao();
        return new BorrowServiceImpl(borrowsDao);
    }

    @Override
    public ApiResponse<List<Borrow>> getOngoingBorrows(Long userId) {
        try{
            List<Borrow> results = borrowsDao.getOngoingBorrows(userId);
            return ApiResponse.success(results);
        }catch (Exception e){
            logger.error("Query OngoingBorrows failed, userId = " + userId, e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

    @Override
    public ApiResponse<Integer> countBorrowedBookByBookId(Long bookId) {
        try{
            int result = borrowsDao.countBorrowedBookByBookId(bookId);
            return ApiResponse.success(result);
        }catch (Exception e){
            logger.error("Query Borrow by bookId failed, bookId = " + bookId, e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

    @Override
    public ApiResponse<Integer> insertBorrow(Borrow borrow) {
        try{
            int result = borrowsDao.insertBorrow(borrow);
            return ApiResponse.success(result);
        }catch (Exception e){
            logger.error("Insert Borrow failed, borrow = " + borrow, e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

    @Override
    public ApiResponse<Integer> userBorrowCount(long bookId) {
        try{
            int result = borrowsDao.userBorrowCount(bookId);
            return ApiResponse.success(result);
        }catch (Exception e){
            logger.error("User borrow count query failed ", e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

    @Override
    public ApiResponse updateBorrowStatus(Long borrowId, char borrowStatus) {
        try{
            Borrow borrow = new Borrow();
            borrow.setBorrowId(borrowId);
            borrow.setBorrowStatus(borrowStatus);
            borrowsDao.updateBorrowStatus(borrowId, borrowStatus);
            return ApiResponse.success();
        }catch (Exception e){
            logger.error("Update Borrow failed, borrowId = " + borrowId + ", borrowStatus = " + borrowStatus, e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }

    @Override
    public ApiResponse updateExtensionsAndExpectedReturnDate(Long borrowId, int extensionCount, Date expectedReturnDate) {
        try{
            borrowsDao.updateExtensionsAndExpectedReturnDate(borrowId, extensionCount, expectedReturnDate);
            return ApiResponse.success();
        }catch (Exception e){
            logger.error("Update Extensions failed, borrowId = " + borrowId + ", extensionCount = " + extensionCount, e);
            return ApiResponse.error(ApiResponseCode.ERROR_DATABASE);
        }
    }


}
