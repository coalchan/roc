package com.luckypeng.roc.common.util;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhipeng
 */
public class SysUtil {
    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<URL> findJarsInDir(File dir)  throws MalformedURLException {
        List<URL> urlList = new ArrayList<>();

        if(dir.exists() && dir.isDirectory()) {
            File[] jarFiles = dir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".jar");
                }
            });

            for(File jarFile : jarFiles) {
                urlList.add(jarFile.toURI().toURL());
            }

        }

        return urlList;
    }
}
