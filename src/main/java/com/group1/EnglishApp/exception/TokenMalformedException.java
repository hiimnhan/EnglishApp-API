package com.group1.EnglishApp.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Hai Dang
 */
public class TokenMalformedException extends AuthenticationException {
    public TokenMalformedException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public TokenMalformedException(String detailMessage) {
        super(detailMessage);
    }
}
