package com.luckypeng.roc.core.run;

import com.luckypeng.roc.common.util.SysUtil;
import com.luckypeng.roc.core.config.MockConfig;
import com.luckypeng.roc.core.writer.Writer;
import com.luckypeng.roc.mock.Mock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

@Slf4j
public class RocTask implements Runnable {
    private Writer writer;
    private MockConfig mockConfig;

    public RocTask(Writer writer, MockConfig mockConfig) {
        this.writer = writer;
        this.mockConfig = mockConfig;
    }

    @Override
    public void run() {
        while (true) {
            int length = RandomUtils.nextInt(1, mockConfig.getMaxLength()+1);
            Object[][] data = Mock.mock(mockConfig.getRule(), length);
            try {
                write(data);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            // TODO 限速
            SysUtil.sleep(1000);
        }
    }

    private void write(Object[][] data) throws Exception {
        if (writer.canParallel()) {
            writer.writeData(data);
        } else {
            synchronized (mockConfig) {
                writer.writeData(data);
            }
        }
    }
}
