package com.luckypeng.roc.core.config;

import lombok.Getter;

import java.util.Map;

/**
 * Content config
 * @author chenzhipeng
 */
@Getter
public class ContentConfig extends AbstractConfig {

    public final static String KEY_READER_CONFIG = "reader";
    public final static String KEY_WRITER_CONFIG = "writer";

    MockConfig mock;
    WriterConfig writer;

    public ContentConfig(Map<String, Object> map) {
        super(map);
        if(map != null) {
            mock = new MockConfig((Map<String, Object>) map.get(KEY_READER_CONFIG));
            writer = new WriterConfig((Map<String, Object>) map.get(KEY_WRITER_CONFIG));
        }
    }
}
