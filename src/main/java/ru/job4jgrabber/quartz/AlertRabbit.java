package ru.job4jgrabber.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class AlertRabbit {
    public static void main(String[] args) {
//        Scheduler scheduler = null;
//        try (InputStream in = new FileInputStream("src/main/resources/rabbit.properties")) {
//            Properties file = new Properties();
//            file.load(in);
//            scheduler = StdSchedulerFactory.getDefaultScheduler();
//            scheduler.start();
//        JobDetail job = newJob(Rabbit.class).build();
//        SimpleScheduleBuilder times = simpleSchedule()
//                .withIntervalInSeconds(Integer.parseInt(file.getProperty("rabbit.interval")))
//                .repeatForever();
//        Trigger trigger = newTrigger()
//                .startNow()
//                .withSchedule(times)
//                .build();
//            scheduler.scheduleJob(job, trigger);
//        } catch (SchedulerException  | IOException se) {
//            se.printStackTrace();
//        }

        try (InputStream in = new FileInputStream("src/main/resources/rabbit.properties")) {
            Properties file = new Properties();
            file.load(in);
            Class.forName(file.getProperty("driver-class-name"));
            try (Connection cnt = DriverManager.getConnection(
                    file.getProperty("url"),
                    file.getProperty("username"),
                    file.getProperty("password"))) {
                Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
                scheduler.start();
                JobDataMap data = new JobDataMap();
                data.put("store", cnt);
                JobDetail job = newJob(Rabbit.class)
                        .usingJobData(data)
                        .build();
                SimpleScheduleBuilder times = simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever();
                Trigger trigger = newTrigger()
                        .startNow()
                        .withSchedule(times)
                        .build();
                scheduler.scheduleJob(job, trigger);
                Thread.sleep(5000);
                scheduler.shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Rabbit implements Job {
        public Rabbit() {
            System.out.println(hashCode());
        }

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here...");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Connection cnt = (Connection) context.getJobDetail().getJobDataMap().get("store");
            try (PreparedStatement st = cnt.prepareStatement("INSERT INTO rabbit (created_date) VALUES (?)")) {
                st.setString(1, format.format(new Date()));
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
