package com.luckypeng.roc.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author chenzhipeng
 * @date 2018/11/22 11:33
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    public static String fromFile(String fileName) {
        File file = new File(fileName);
        try {
            FileInputStream in = new FileInputStream(file);
            byte[] fileContent = new byte[(int) file.length()];
            in.read(fileContent);
            return new String(fileContent, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
