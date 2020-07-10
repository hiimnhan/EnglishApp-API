package com.group1.EnglishApp.security;

import com.google.gson.Gson;
import com.group1.EnglishApp.enumeration.ErrorCode;
import com.group1.EnglishApp.enumeration.ResponseStatus;
import com.group1.EnglishApp.response.GenericResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Hai Dang
 */
@Component
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        GenericResponse<?> responseJson = new GenericResponse<>();
        responseJson.setStatus(ResponseStatus.FAILED.getStatus());
        if (exception instanceof AccountStatusException) {
            responseJson.setCode(ErrorCode.ERROR_INACTIVE_USER.getCode());
            responseJson.setMessage(ErrorCode.ERROR_INACTIVE_USER.getMessage());
        } else {
            responseJson.setCode(ErrorCode.ERROR_BAD_TOKEN.getCode());
            responseJson.setMessage(ErrorCode.ERROR_BAD_TOKEN.getMessage());
        }
        responseJson.setException(exception.getMessage());

        Gson gson = new Gson();
        String responseStr = gson.toJson(responseJson);

        try (PrintWriter writer = response.getWriter()) {
            writer.write(responseStr);
            writer.flush();
        }
    }
}
