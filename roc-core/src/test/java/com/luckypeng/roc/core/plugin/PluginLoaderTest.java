package com.luckypeng.roc.core.plugin;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;

@Slf4j
public class PluginLoaderTest {
    @Test
    @Ignore
    public void testGetPluginClass() {
        PluginLoader pluginLoader = new PluginLoader("mysqlwriter", "../plugins");
        log.info(pluginLoader.getPluginClass().toString());
    }

}