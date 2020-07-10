package com.group1.EnglishApp.service;

import com.group1.EnglishApp.dto.UserDto;
import com.group1.EnglishApp.exception.EnglishAppException;
import com.group1.EnglishApp.form.LoginForm;
import com.group1.EnglishApp.mapper.UserMapper;
import com.group1.EnglishApp.model.User;
import com.group1.EnglishApp.security.TokenParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

/**
 * @author Hai Dang
 */
@Service
public class AuthenticationService {

    @Autowired
    @Qualifier(value = "authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenParser tokenParser;

    @Autowired
    private UserMapper userMapper;

    public UserDto processLogin(LoginForm loginForm, WebAuthenticationDetails webAuthDetails) throws EnglishAppException {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
        token.setDetails(webAuthDetails);

        Authentication authenticatedUser = authenticationManager.authenticate(token);
        Object principalObj = authenticatedUser.getPrincipal();
        User user = (User) principalObj;

        if (user.isEnabled() == false) {
            throw new EnglishAppException("User is inactive");
        }

        String accessToken = tokenParser.generateUserToken(user);
        UserDto userDto = userMapper.toDto(user);
        userDto.setAccessToken(accessToken);
        return userDto;
    }
}
