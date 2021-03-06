package com.luckypeng.roc.core.writer;

import com.luckypeng.roc.core.config.RocConfig;
import com.luckypeng.roc.writer.mysql.MysqlWriter;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author chenzhipeng
 * @date 2018/11/22 14:32
 */
public class WriterFactoryTest {
    @Test
    @Ignore
    public void getWriter() {
        RocConfig rocConfig = RocConfig.parse("../roc-test/tests/test.json");
        Writer writer = WriterFactory.getWriter(rocConfig);
        assertTrue(writer instanceof MysqlWriter);
    }
}
