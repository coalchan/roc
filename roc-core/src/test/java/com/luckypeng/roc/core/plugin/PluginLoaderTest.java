package com.luckypeng.roc.core.plugin;

import org.junit.Test;

public class PluginLoaderTest {
    @Test
    public void testGetPluginClass() {
        PluginLoader pluginLoader = new PluginLoader("mysqlwriter", "../plugins");
        System.out.println(pluginLoader.getPluginClass());
    }

}