package com.luckypeng.roc.core.plugin;

import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import static org.junit.Assert.*;

public class PluginLoaderTest {
    @Test
    public void test() {
        URLClassLoader cl = null;
        try {
            // 从Jar文件得到一个Class加载器
            cl = new URLClassLoader(new URL[] { new URL("file:D:/other-code/roc/plugins/mysqlwriter/roc-writer-mysql.jar") },
                    Thread.currentThread().getContextClassLoader());
            // 从加载器中加载Class
            Class<?> c = cl.loadClass("com.luckypeng.roc.writer.mysql.MysqlWriter");
            // 从Class中实例出一个对象
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cl != null) {
                try {
                    cl.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}