package com.luckypeng.roc.core.data;

import lombok.Data;

/**
 * @author chenzhipeng
 */
@Data
public class Record {
    private int byteSize;
    private Object[] data;

    public Record(Object[] data) {
        this.data = data;
        this.byteSize = computeSize();
    }

    private int computeSize() {
        int size = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] instanceof Boolean) {
                size += 1;
            } else {
                size += data[i].toString().length();
            }
        }
        return size;
    }
}
