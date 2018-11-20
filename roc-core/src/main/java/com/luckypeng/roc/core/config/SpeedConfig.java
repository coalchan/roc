package com.luckypeng.roc.core.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Speed config
 * @author chenzhipeng
 */
public class SpeedConfig extends AbstractConfig {

    public static final String KEY_BYTES = "bytes";
    public static final String KEY_NUM_CHANNELS = "channel";

    public static final long DEFAULT_SPEED_BYTES = Long.MAX_VALUE;
    public static final int DEFAULT_NUM_CHANNELS = 1;

    public SpeedConfig(Map<String, Object> map) {
        super(map);
    }

    public static SpeedConfig defaultConfig(){
        Map<String, Object> map = new HashMap<>(2);
        map.put("bytes",DEFAULT_SPEED_BYTES);
        map.put("channel",DEFAULT_NUM_CHANNELS);
        return new SpeedConfig(map);
    }

    public long getBytes() {
        return getLongVal(KEY_BYTES, DEFAULT_SPEED_BYTES);
    }

    public int getChannel() {
        return getIntVal(KEY_NUM_CHANNELS, DEFAULT_NUM_CHANNELS);
    }
}
