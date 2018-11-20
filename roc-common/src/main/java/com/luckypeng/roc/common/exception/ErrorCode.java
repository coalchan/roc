package com.luckypeng.roc.common.exception;

/**
 * 错误码统一接口
 * @author chenzhipeng
 */
public interface ErrorCode {
	/**
	 * 错误码编号
	 * @return
	 */
	String getCode();

	/**
	 * 错误码描述
	 * @return
	 */
	String getDescription();

	/**
	 * toString
	 * @return
	 */
	@Override
	String toString();
}
