package com.luckypeng.roc.core.config;

import lombok.Getter;

import java.util.Map;

/**
 * setting config
 * @author chenzhipeng
 */
@Getter
public class SettingConfig extends AbstractConfig {

    public static final String KEY_SPEED_CONFIG = "speed";

    private SpeedConfig speed = SpeedConfig.defaultConfig();

    /**
     * 写入数据量： 0表示一直写
     */
    private int num;

    public SettingConfig(Map<String, Object> map) {
        super(map);
        if(map.containsKey(KEY_SPEED_CONFIG)) {
            speed = new SpeedConfig((Map<String, Object>) map.get(KEY_SPEED_CONFIG));
        }
    }
}

