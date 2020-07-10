package com.group1.EnglishApp.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @author Hai Dang
 */
public class AuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 1L;
    private final String token;

    public AuthenticationToken(String token) {

        super(null, null);
        this.token = token;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }
}
