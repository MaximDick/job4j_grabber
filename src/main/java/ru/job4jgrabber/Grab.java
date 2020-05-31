package ru.job4jgrabber;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

public interface Grab {
    /**
     * Метод для запуска приложения.
     *
     * @param parse     - parse
     * @param store     - storage
     * @param scheduler -  scheduler
     * @throws SchedulerException
     */
    void init(Parse parse, Store store, Scheduler scheduler) throws SchedulerException;
}
