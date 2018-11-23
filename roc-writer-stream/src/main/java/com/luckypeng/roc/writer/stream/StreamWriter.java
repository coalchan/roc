package com.luckypeng.roc.writer.stream;

import com.luckypeng.roc.core.config.RocConfig;
import com.luckypeng.roc.core.data.Record;
import com.luckypeng.roc.core.writer.Writer;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * test writer
 * @author chenzhipeng
 */
public class StreamWriter extends Writer {
    public StreamWriter(RocConfig rocConfig) {
        super(rocConfig);
    }

    @Override
    public void writeData(Record[] records) {
        for (int i = 0; i < records.length; i++) {
            String content = Arrays.stream(records[i].getData()).map(Object::toString).collect(Collectors.joining(", ", "{", "}"));
            System.out.println(content);
        }
    }
}
