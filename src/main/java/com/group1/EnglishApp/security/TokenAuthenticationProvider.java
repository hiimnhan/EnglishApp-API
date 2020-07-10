package com.group1.EnglishApp.security;

import com.group1.EnglishApp.model.User;
import com.group1.EnglishApp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Hai Dang
 */
@Component
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationProvider.class);

    @Autowired
    private TokenParser tokenParser;
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> authentication) {
        return AuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        AuthenticationToken authenticationToken = (AuthenticationToken) authentication;
        String token = authenticationToken.getToken();

        logger.debug("Token: {}", token);

        User user = tokenParser.parseUserToken(token);
        // overwrite user for more details
        if (user.getId() != null) {
            Optional<User> userOpt = userService.getById(user.getId());
            if (userOpt.isPresent() == false) {
                throw new UsernameNotFoundException(String.format("Username [%s] not found", user.getUsername()));
            } else {
                user = userOpt.get();
                if (user.isEnabled() == false) {
                    throw new DisabledException(String.format("Username [%s] is inactive", user.getUsername()));
                }
            }
            user.setAccessToken(token);
        } else {
            throw new UsernameNotFoundException(String.format("Username is not found in this token: %s", token));
        }
        return user;
    }
}
