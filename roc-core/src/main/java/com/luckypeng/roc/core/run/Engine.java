package com.luckypeng.roc.core.run;

import com.luckypeng.roc.core.config.RocConfig;
import com.luckypeng.roc.core.writer.Writer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenzhipeng
 * @date 2018/11/16 16:06
 */
public class Engine {
    private RocConfig rocConfig;
    private Writer writer;

    public Engine(RocConfig rocConfig, Writer writer) {
        this.rocConfig = rocConfig;
        this.writer = writer;
    }

    public void start() throws Exception {
        int parallelNum = rocConfig.getJob().getSetting().getSpeed().getChannel();
        ExecutorService executorService = Executors.newFixedThreadPool(parallelNum);

        for (int i = 0; i < parallelNum; i++) {
            Runnable task = new RocTask(writer, rocConfig.getJob().getContent().getMock());
            executorService.submit(task);
        }
    }
}
