package com.luckypeng.roc.core.config;

import com.alibaba.fastjson.JSON;
import com.luckypeng.roc.common.exception.CommonErrorCode;
import com.luckypeng.roc.common.util.AssertionUtils;
import lombok.Getter;

import java.util.Map;

/**
 * @author chenzhipeng
 * @date 2018/11/20 16:46
 */
@Getter
public class RocConfig extends AbstractConfig {
    private JobConfig job;

    public static final String KEY_JOB_CONFIG = "job";

    public RocConfig(Map<String, Object> map) {
        super(map);
        job = new JobConfig((Map<String, Object>) map.get(KEY_JOB_CONFIG));
    }

    private String pluginRoot;

    /**
     * Job配置解析
     * @param job
     */
    public static RocConfig parse(String job) {
        Map<String, Object> map = JSON.parseObject(job);
        RocConfig rocConfig = new RocConfig(map);
        checkConfig(rocConfig);
        return rocConfig;
    }

    /**
     * 检查配置
     * @param rocConfig
     */
    private static void checkConfig(RocConfig rocConfig) {
        AssertionUtils.isNotEmpty(rocConfig, CommonErrorCode.CONFIG_ERROR, "配置不能为空");

        JobConfig job = rocConfig.getJob();
        AssertionUtils.isNotEmpty(job, CommonErrorCode.CONFIG_ERROR, "job不能为空");

        ContentConfig content = job.getContent();

        MockConfig mockConfig = content.getMock();
        // check mock
        AssertionUtils.isNotEmpty(mockConfig, CommonErrorCode.CONFIG_ERROR, "mock不能为空");
        AssertionUtils.isNotEmpty(mockConfig.getRule(), CommonErrorCode.CONFIG_ERROR, "mock:rule不能为空");

        WriterConfig writerConfig = content.getWriter();
        // check writer
        AssertionUtils.isNotEmpty(writerConfig, CommonErrorCode.CONFIG_ERROR, "writer不能为空");
        AssertionUtils.isNotEmpty(writerConfig.getName(), CommonErrorCode.CONFIG_ERROR, "writer名称不能为空");
        AssertionUtils.isNotEmpty(writerConfig.getParameter(), CommonErrorCode.CONFIG_ERROR, "writer参数不能为空");
    }
}
