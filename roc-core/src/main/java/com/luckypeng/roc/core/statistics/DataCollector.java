package com.luckypeng.roc.core.statistics;

import com.luckypeng.roc.core.data.Record;
import lombok.Getter;

/**
 * @author chenzhipeng
 */
public class DataCollector {
    private static DataCollector collector = new DataCollector();

    @Getter
    private static long totalByteSize = 0;

    @Getter
    private static int totalRecords = 0;

    private DataCollector() {}

    public static DataCollector getInstance() {
        return collector;
    }

    public synchronized void collectorInfo(Record[] records) {
        // 其实每次都是一个随机大小的数组，这里看成一个记录，所以加1
        totalRecords += 1;
        for (Record record: records) {
            totalByteSize += record.getByteSize();
        }
    }
}
