package com.group1.EnglishApp.service;

import com.group1.EnglishApp.exception.EnglishAppException;
import com.group1.EnglishApp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Hai Dang
 */
@Service
public class UserDetailService implements UserDetailsService {
    private final static Logger logger = LoggerFactory.getLogger(UserDetailService.class);

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.debug("Calling method loadUserByUsername({})", username);
        try {
            User user = userService.getUserByUsername(username);
            logger.debug("Returning method loadUserByUsername({}) = {}", username, user);
            return user;
        } catch (EnglishAppException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }

    }
}
