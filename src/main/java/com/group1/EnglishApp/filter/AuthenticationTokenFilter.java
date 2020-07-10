package com.group1.EnglishApp.filter;

import com.group1.EnglishApp.exception.TokenMissingException;
import com.group1.EnglishApp.security.AuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hai Dang
 */
public class AuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {
    public final String HEADER_AUTHORIZATION = "Authorization";
    public final String ACCESS_TOKEN = "access_token";

    public AuthenticationTokenFilter() {
        super("/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        String authToken = extractToken(request);
        AuthenticationToken authRequest = new AuthenticationToken(authToken);
        return getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        // As this authentication is in HTTP header, after success we need to continue the request normally
        // and return the response as if the resource was not secured at all
        chain.doFilter(request, response);
    }

    /**
     *
     * @param request
     * @return
     */
    private String extractToken(HttpServletRequest request) {

        String header = request.getHeader(HEADER_AUTHORIZATION);
        String accessTokenParam = request.getParameter(ACCESS_TOKEN);
        String token = "";

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
        } else if (accessTokenParam != null) {
            token = accessTokenParam;
        } else {
            throw new TokenMissingException("No token found in request headers");
        }

        return token;
    }
}
