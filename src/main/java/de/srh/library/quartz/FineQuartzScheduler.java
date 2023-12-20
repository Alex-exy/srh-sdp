package de.srh.library.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FineQuartzScheduler {

    private static Logger logger = LoggerFactory.getLogger(FineQuartzScheduler.class);

    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("fineTrigger", "defaultGroup")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(1 * 10) // 10s
                            .repeatForever())
                    .build();

            JobDetail job = JobBuilder.newJob(FineJob.class)
                    .withIdentity("fineJob", "defaultGroup")
                    .build();

            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            logger.error("fineJob executed failed", e);
        }
    }
}
