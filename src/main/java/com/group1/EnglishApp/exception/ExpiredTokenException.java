package com.group1.EnglishApp.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Hai Dang
 */
public class ExpiredTokenException extends AuthenticationException {
    public ExpiredTokenException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ExpiredTokenException(String detailMessage) {
        super(detailMessage);
    }
}
