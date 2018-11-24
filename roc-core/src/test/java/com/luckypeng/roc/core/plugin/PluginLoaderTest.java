package com.luckypeng.roc.core.plugin;

import org.junit.Ignore;
import org.junit.Test;

public class PluginLoaderTest {
    @Test
    @Ignore
    public void testGetPluginClass() {
        PluginLoader pluginLoader = new PluginLoader("mysqlwriter", "../plugins");
        System.out.println(pluginLoader.getPluginClass());
    }

}