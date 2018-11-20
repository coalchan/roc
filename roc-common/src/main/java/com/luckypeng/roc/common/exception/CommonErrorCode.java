package com.luckypeng.roc.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chenzhipeng
 */

@Getter
@AllArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    PARAM_ERROR("Common-01", "您提供的参数存在错误."),
    CONFIG_ERROR("Common-00", "您提供的配置文件存在错误信息，请检查您的作业配置 .");

    private final String code;
    private final String description;

    @Override
    public String toString() {
        return String.format("Code:[%s], Describe:[%s]", this.code, this.description);
    }
}
