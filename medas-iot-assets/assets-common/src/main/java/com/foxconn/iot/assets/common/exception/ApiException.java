package com.foxconn.iot.assets.common.exception;

import com.foxconn.iot.assets.common.api.IErrorCode;

/**
 * 自定义API异常
 */
public class ApiException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private IErrorCode errorCode;
	
	public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
