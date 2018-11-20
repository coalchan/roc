package com.luckypeng.roc.core.writer;

import com.luckypeng.roc.core.config.RocConfig;
import com.luckypeng.roc.core.config.WriterConfig;

public class WriterFactory {
    private WriterFactory() {}

    public static Writer getWriter(RocConfig rocConfig) {
        WriterConfig writerConfig = rocConfig.getJob().getContent().getWriter();
        // TODO get writer
        return null;
    }
}
