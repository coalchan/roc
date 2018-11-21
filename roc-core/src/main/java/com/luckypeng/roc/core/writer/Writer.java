package com.luckypeng.roc.core.writer;

import com.luckypeng.roc.core.config.RocConfig;

/**
 * @author chenzhipeng
 * @date 2018/11/15 15:20
 */
public abstract class Writer {
    public Writer(RocConfig rocConfig) {

    }

    /**
     * 写数据
     * @param data
     * @throws Exception
     */
    public abstract void writeData(Object[] data) throws Exception;
}
