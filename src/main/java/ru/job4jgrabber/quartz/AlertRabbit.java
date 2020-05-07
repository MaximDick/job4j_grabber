package ru.job4jgrabber.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class AlertRabbit {
    public static void main(String[] args) {
        Scheduler scheduler = null;
        try (InputStream in = new FileInputStream("src/main/resources/rabbit.properties")) {
            Properties file = new Properties();
            file.load(in);
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
        JobDetail job = newJob(Rabbit.class).build();
        SimpleScheduleBuilder times = simpleSchedule()
                .withIntervalInSeconds(Integer.parseInt(file.getProperty("rabbit.interval")))
                .repeatForever();
        Trigger trigger = newTrigger()
                .startNow()
                .withSchedule(times)
                .build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException  | IOException se) {
            se.printStackTrace();
        }
    }

    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here...");
        }
    }
}
