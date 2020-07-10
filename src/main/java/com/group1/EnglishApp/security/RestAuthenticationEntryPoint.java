package com.group1.EnglishApp.security;

import com.google.gson.Gson;
import com.group1.EnglishApp.enumeration.ErrorCode;
import com.group1.EnglishApp.enumeration.ResponseStatus;
import com.group1.EnglishApp.response.GenericResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
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
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpRequest, HttpServletResponse httpResponse, AuthenticationException authException) throws IOException, ServletException {

        httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        GenericResponse<?> responseJson = new GenericResponse<>();
        responseJson.setStatus(ResponseStatus.FAILED.getStatus());
        responseJson.setCode(ErrorCode.ERROR_UNAUTHENTICATED.getCode());
        responseJson.setMessage(ErrorCode.ERROR_UNAUTHENTICATED.getMessage());
        responseJson.setException(authException.getMessage());

        Gson gson = new Gson();
        String responseStr = gson.toJson(responseJson);

        try (PrintWriter writer = httpResponse.getWriter()) {
            writer.write(responseStr);
            writer.flush();
        }
    }
}
