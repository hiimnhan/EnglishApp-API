package com.group1.EnglishApp.exception;

import com.group1.EnglishApp.enumeration.ErrorCode;

/**
 * @author Hai Dang
 */
public class EnglishAppException extends Exception{
    private ErrorCode errorCode;

    public EnglishAppException(ErrorCode errorCode, String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        this.errorCode = errorCode;
    }

    public EnglishAppException(ErrorCode errorCode, String detailMessage) {
        super(detailMessage);
        this.errorCode = errorCode;
    }

    public EnglishAppException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public EnglishAppException(Throwable throwable) {
        super(throwable);
    }

    public EnglishAppException(String detailMessage) {
        super(detailMessage);
    }

    public EnglishAppException() {
    }

    /**
     * @return the errorCode
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
