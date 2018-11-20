package com.luckypeng.roc.core.config;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Job配置
 * @author chenzhipeng
 */
@Getter
public class JobConfig extends AbstractConfig {

    public static final String KEY_SETTING_CONFIG = "setting";
    public static final String KEY_CONTENT_CONFIG = "content";

    private SettingConfig setting;
    private ContentConfig content;

    public JobConfig(Map<String, Object> map) {
        super(map);
        setting = new SettingConfig((Map<String, Object>) map.get(KEY_SETTING_CONFIG));
        content = new ContentConfig((Map<String, Object>) map.get(KEY_CONTENT_CONFIG));
    }
}
