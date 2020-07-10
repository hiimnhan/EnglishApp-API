package com.group1.EnglishApp.exception;
/**
 * @author Hai Dang
 */
public class EnglishAppValidationException extends Exception{
    private static final long serialVersionUID = 1L;

    public EnglishAppValidationException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public EnglishAppValidationException(Throwable throwable) {
        super(throwable);
    }

    public EnglishAppValidationException(String detailMessage) {
        super(detailMessage);
    }

    public EnglishAppValidationException() {
    }
}
