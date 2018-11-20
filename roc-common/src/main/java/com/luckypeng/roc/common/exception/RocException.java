package com.luckypeng.roc.common.exception;

import lombok.Getter;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author chenzhipeng
 * @date 2018/10/22 15:23
 */
@Getter
public class RocException extends RuntimeException {
    private static final long serialVersionUID = 5066807044730258274L;

    private ErrorCode errorCode;

    public RocException(ErrorCode errorCode, String message) {
        super(errorCode.toString() + " - " + message);
        this.errorCode = errorCode;
    }

    public RocException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode.toString() + " - " + getMessage(message) + " - " + getMessage(cause), cause);
        this.errorCode = errorCode;
    }

    public static RocException asRocException(ErrorCode errorCode, String message) {
        return new RocException(errorCode, message);
    }

    public static RocException asRocException(ErrorCode errorCode, String message, Throwable cause) {
        if (cause instanceof RocException) {
            return (RocException) cause;
        }
        return new RocException(errorCode, message, cause);
    }

    public static RocException asRocException(ErrorCode errorCode, Throwable cause) {
        if (cause instanceof RocException) {
            return (RocException) cause;
        }
        return new RocException(errorCode, getMessage(cause), cause);
    }
    
    private static String getMessage(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof Throwable) {
            StringWriter str = new StringWriter();
            PrintWriter pw = new PrintWriter(str);
            ((Throwable) obj).printStackTrace(pw);
            return str.toString();
        } else {
            return obj.toString();
        }
    }
}
