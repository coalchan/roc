package com.luckypeng.roc.core.writer;

import com.luckypeng.roc.core.config.RocConfig;
import com.luckypeng.roc.core.config.WriterConfig;
import com.luckypeng.roc.core.plugin.PluginLoader;

import java.lang.reflect.Constructor;

/**
 * @author chenzhipeng
 */
public class WriterFactory {
    private WriterFactory() {}

    public static Writer getWriter(RocConfig rocConfig) {
        WriterConfig writerConfig = rocConfig.getJob().getContent().getWriter();

        PluginLoader pluginLoader = new PluginLoader(writerConfig.getName(), rocConfig.getPluginRoot());
        Class<?> clz = pluginLoader.getPluginClass();

        try {
            Constructor constructor = clz.getConstructor(RocConfig.class);
            return (Writer) constructor.newInstance(rocConfig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
