package com.example.mobileservice.task;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final Random random = new Random();

    public void executeTask(final Task task) {
        scheduler.schedule((Runnable) task::execute, calculateReandomDelay(), TimeUnit.SECONDS);
    }

    private int calculateReandomDelay() {
        return random.nextInt(9) + 1;
    }
}
