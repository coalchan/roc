package com.luckypeng.roc.core.run;

import com.luckypeng.roc.core.config.MockConfig;
import com.luckypeng.roc.core.data.Record;
import com.luckypeng.roc.core.statistics.ByteRateLimiter;
import com.luckypeng.roc.core.statistics.DataCollector;
import com.luckypeng.roc.core.writer.Writer;
import com.luckypeng.roc.mock.Mock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

/**
 * @author chenzhipeng
 */
@Slf4j
public class RocTask implements Runnable {
    private Writer writer;
    private MockConfig mockConfig;
    private ByteRateLimiter rateLimiter;
    private DataCollector collector = DataCollector.getInstance();
    private boolean isLimit = false;

    public RocTask(Writer writer, MockConfig mockConfig) {
        this.writer = writer;
        this.mockConfig = mockConfig;
    }

    public void setRateLimiter(ByteRateLimiter rateLimiter) {
        this.isLimit = true;
        this.rateLimiter = rateLimiter;
    }

    @Override
    public void run() {
        while (true) {
            int length = RandomUtils.nextInt(1, mockConfig.getMaxLength()+1);
            Record[] records = mocks(mockConfig.getRule(), length);
            try {
                write(records);
            } catch (Exception e) {
                e.printStackTrace(); // TODO log4j配置
                log.error(e.getMessage(), e);
            }
            // 统计信息
            collector.collectorInfo(records);

            if (isLimit) {
                // 限速
                rateLimiter.acquire();
            }
        }
    }

    /**
     * 生成最大长度为 maxLength 的mock记录
     * @param rule
     * @param maxLength
     * @return
     */
    private Record[] mocks(List<String> rule, int maxLength) {
        Record[] records = new Record[maxLength];
        for (int i = 0; i < records.length; i++) {
            records[i] = new Record(Mock.mock(rule));
        }
        return records;
    }

    private void write(Record[] records) throws Exception {
        if (writer.canParallel()) {
            writer.writeData(records);
        } else {
            synchronized (mockConfig) {
                writer.writeData(records);
            }
        }
    }
}
