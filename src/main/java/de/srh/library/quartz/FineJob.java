package de.srh.library.quartz;

import cn.hutool.core.collection.ListUtil;
import de.srh.library.dao.BookDao;
import de.srh.library.dao.BorrowsDao;
import de.srh.library.dao.UserDao;
import de.srh.library.dto.BookDto;
import de.srh.library.dto.OverdueBook;
import de.srh.library.util.DateUtil;
import de.srh.library.util.EmailSender;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FineJob implements Job {

    private static Logger logger = LoggerFactory.getLogger(FineJob.class);

    private BorrowsDao borrowsDao = new BorrowsDao();

    private UserDao userDao = new UserDao();

    private BookDao bookDao = new BookDao();


    @Override
    public void execute(JobExecutionContext context) {
        logger.info("FineJob executed....");
        borrowsDao.updateBorrowStatusWhenDelayed();

        List<Long> userIds = borrowsDao.findDelayedUserIds();
        userIds.forEach(userId -> {
            List<OverdueBook> overdueBooks = new ArrayList<>();
            borrowsDao.findOverdueBooksByUserId(userId).forEach(borrow -> {
                BookDto bookDto = bookDao.getBookById(borrow.getBookId());
                int overdueDays = (int) DateUtil.daysToToday(borrow.getExpectedReturnDate());
                String fine = bookDto.getFine( overdueDays);
                overdueBooks.add(new OverdueBook(bookDto.getBookName(), overdueDays, fine));
            });
           String emailAddress = userDao.getUserById(userId).getEmail();
           sendFineNotification(emailAddress, Map.of("books", overdueBooks));
        });
        logger.info("FineJob finished....");
    }

    private void sendFineNotification(String emailAddress, Map<String, Object> dataModel) {
        EmailSender.send(ListUtil.toList(emailAddress),
                "Fine-Notification - Heidelberg Library",
                dataModel,
                "overdue-notification-mail.html",
                true);
    }
}
