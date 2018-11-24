package com.luckypeng.roc.writer.file;

import com.google.common.base.Charsets;
import com.luckypeng.roc.common.exception.CommonErrorCode;
import com.luckypeng.roc.common.util.AssertionUtils;
import com.luckypeng.roc.common.util.DateUtils;
import com.luckypeng.roc.common.util.StringUtils;
import com.luckypeng.roc.core.config.RocConfig;
import com.luckypeng.roc.core.config.WriterConfig.ParameterConfig;
import com.luckypeng.roc.core.data.Record;
import com.luckypeng.roc.core.writer.Writer;
import org.apache.commons.lang3.EnumUtils;

import java.io.*;

import static com.luckypeng.roc.writer.file.FileConfigKeys.*;

/**
 * @author chenzhipeng
 * @date 2018/11/24 13:53
 */
public class FileWriter extends Writer {
    /**
     * 文件存放路径
     */
    private String fileDir;

    /**
     * 文件递增方式
     */
    private AddBy addBy;

    /**
     * 时间格式
     */
    private String dateFormat;

    /**
     * 文件后缀
     */
    private String fileSuffix;

    /**
     * 文件完整路径
     */
    private String filePath;

    /**
     * 文件最大大小
     */
    private Long maxSize;

    /**
     * 字段分隔符
     */
    private String fileDelimiter;

    /**
     * 文件名前缀
     */
    private String filePrefix;

    private BufferedWriter bw;

    private static final String DEFAULT_ADD_BY = "Time";
    private static final long DEFAULT_FILE_MAX_SIZE = 1024 * 1024;
    private static final String DEFAULT_FORMAT = "yyyyMMddHHmm";
    private static final String DEFAULT_FILE_DELIMITER = "\u0001";
    private static final String DEFAULT_FILE_PREFIX = "file";

    private Object lock = new Object();

    public FileWriter(RocConfig rocConfig) {
        super(rocConfig);
        ParameterConfig parameters = rocConfig.getJob().getContent().getWriter().getParameter();
        this.fileDir = parameters.getStringVal(KEY_FILE_DIR);
        AssertionUtils.isNotEmpty(fileDir, CommonErrorCode.CONFIG_ERROR, "fileDir不能为空");
        this.addBy = EnumUtils.getEnum(AddBy.class, parameters.getStringVal(KEY_ADD_BY, DEFAULT_ADD_BY));
        this.dateFormat = parameters.getStringVal(KEY_TIME_FORMAT, DEFAULT_FORMAT);
        this.maxSize = parameters.getLongVal(KEY_SIZE_MAX_SIZE, DEFAULT_FILE_MAX_SIZE);
        this.fileDelimiter = parameters.getStringVal(KEY_FIELD_DELIMITER, DEFAULT_FILE_DELIMITER);
        this.filePrefix = parameters.getStringVal(KEY_FILE_PREFIX, DEFAULT_FILE_PREFIX);
    }

    @Override
    public void writeData(Record[] records) throws IOException {
        synchronized (lock) {
            String newSuffix = getFileSuffix();
            if (newSuffix != null && !newSuffix.equals(this.fileSuffix)) {
                // 记录之前的writer
                BufferedWriter preBw = bw;
                this.fileSuffix = newSuffix;
                this.filePath = getFilePath();
                this.bw = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(this.filePath, true), Charsets.UTF_8));
                if (preBw != null) {
                    preBw.flush();
                    preBw.close();
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (Record record: records) {
            builder.append(StringUtils.join(record.getData(), fileDelimiter));
            builder.append("\n");
        }
        this.bw.append(builder);
        this.bw.flush();
    }

    /**
     * 获取文件的后缀：考虑文件增长
     * @return
     */
    private String getFileSuffix() {
        String newSuffix = null;
        if (AddBy.Time.equals(this.addBy)) {
            newSuffix = DateUtils.nowDate(this.dateFormat);
        } else if (AddBy.Size.equals(this.addBy) && this.fileSuffix == null) {
            // TODO 这里需要找到当前最大的文件序号去写，这样可以保证随时重启继续写
            newSuffix = "0";
        } else if (AddBy.Size.equals(this.addBy)) {
            File file = new File(this.filePath);
            long fileSize = file.length();
            if (fileSize > this.maxSize) {
                newSuffix = String.valueOf(Integer.parseInt(this.fileSuffix) + 1);
            }
        } else {
            throw new UnsupportedOperationException("暂时不支持这种类型的文件增长方式: " + this.addBy.name());
        }
        return newSuffix;
    }

    private String getFilePath() {
        return this.fileDir + File.separator + this.filePrefix + "_" + this.fileSuffix;
    }
}
