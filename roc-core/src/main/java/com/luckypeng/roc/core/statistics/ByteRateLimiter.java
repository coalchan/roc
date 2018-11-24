package com.luckypeng.roc.core.statistics;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author chenzhipeng
 */
@Slf4j
public class ByteRateLimiter {
    private long expectedSpeedBytes;
    private RateLimiter rateLimiter;
    private ScheduledExecutorService scheduledExecutorService;
    private int channel;

    public ByteRateLimiter(long expectedSpeedBytes, int channel) {
        this.expectedSpeedBytes = expectedSpeedBytes;
        double initialRate = 1000.0;
        this.rateLimiter = RateLimiter.create(initialRate);
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        this.channel = channel;
    }

    public void start() {
        Runnable task = () -> {
            if (DataCollector.getTotalRecords() > 0) {
                double bpr = 1.0 * DataCollector.getTotalByteSize() / DataCollector.getTotalRecords();
                double permitsPerSecond = expectedSpeedBytes / channel / bpr;
                System.out.println("修正限速QPS: " + permitsPerSecond);
                rateLimiter.setRate(permitsPerSecond);
            }
        };
        scheduledExecutorService.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }

    public void acquire() {
        rateLimiter.acquire();
    }
}
