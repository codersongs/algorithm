package com.codersongs.algorithm.common.exception;

/**
 * 数组检查失败异常
 * @author song
 *
 */
public class ArrayCheckFailException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ArrayCheckFailException() {
	}

	public ArrayCheckFailException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ArrayCheckFailException(String message, Throwable cause) {
		super(message, cause);
	}

	public ArrayCheckFailException(String message) {
		super(message);
	}

	public ArrayCheckFailException(Throwable cause) {
		super(cause);
	}
	
}
