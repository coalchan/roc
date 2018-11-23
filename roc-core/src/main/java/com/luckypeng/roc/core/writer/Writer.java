package com.luckypeng.roc.core.writer;

import com.luckypeng.roc.core.config.RocConfig;
import com.luckypeng.roc.core.data.Record;

/**
 * @author chenzhipeng
 * @date 2018/11/15 15:20
 */
public abstract class Writer {
    protected RocConfig rocConfig;

    public Writer(RocConfig rocConfig) {
        this.rocConfig = rocConfig;
    }

    /**
     * 检查配置
     * @return
     */
    public boolean checkConfig() {
        return true;
    }

    /**
     * 是否可以并行
     * @return
     */
    public boolean canParallel() {
        return true;
    }


    /**
     * 写数据
     * @param records
     * @throws Exception
     */
    public abstract void writeData(Record[] records) throws Exception;
}
