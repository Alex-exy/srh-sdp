package de.srh.library.quartz;

import cn.hutool.core.collection.ListUtil;
import de.srh.library.util.EmailSender;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FineJob implements Job {

    private static Logger logger = LoggerFactory.getLogger(FineJob.class);

    @Override
    public void execute(JobExecutionContext context) {
        logger.info("FineJob executed....");
        // TODO: add fine pre calculation logic and send email when fine generated or updated
    }

    private void sendFineNotification(String emailAddress) {
        EmailSender.send(ListUtil.toList(emailAddress),
                "Fine-Notification - Heidelberg Library",
                null,
                "overdue-notification-mail.html",
                true);
    }
}
