package com.luckypeng.roc.core.config;

import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * @author chenzhipeng
 * @date 2018/11/16 16:39
 */
@Getter
public class MockConfig extends AbstractConfig {
    public static final String KEY_RULES = "rule";
    public static final String KEY_MAX_LENGTH = "maxLength";

    public static final int DEFAULT_LENGTH = 1;

    private List<String> rule;


    public MockConfig(Map<String, Object> map) {
        super(map);
        rule = (List<String>) map.get(KEY_RULES);
    }

    /**
     * 每次最多mock条数
     * @return
     */
    public int getMaxLength() {
        return getIntVal(KEY_MAX_LENGTH, DEFAULT_LENGTH);
    }
}
