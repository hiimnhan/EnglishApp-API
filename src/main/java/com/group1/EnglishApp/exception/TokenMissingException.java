package com.group1.EnglishApp.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Hai Dang
 */
public class TokenMissingException extends AuthenticationException {
    public TokenMissingException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public TokenMissingException(String detailMessage) {
        super(detailMessage);
    }
}
