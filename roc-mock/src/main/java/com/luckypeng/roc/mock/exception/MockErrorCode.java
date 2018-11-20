package com.luckypeng.roc.mock.exception;

import com.luckypeng.roc.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chenzhipeng
 * @date 2018/11/15 14:11
 */
@AllArgsConstructor
@Getter
public enum MockErrorCode implements ErrorCode {

    FUNC_RUN_ERROR("Mock-01", "Mock函数运行出错");

    private final String code;
    private final String description;

    @Override
    public String toString() {
        return String.format("Code:[%s], Describe:[%s]", this.code, this.description);
    }
}
