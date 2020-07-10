package com.group1.EnglishApp.security;

import com.google.gson.Gson;
import com.group1.EnglishApp.enumeration.ErrorCode;
import com.group1.EnglishApp.enumeration.ResponseStatus;
import com.group1.EnglishApp.response.GenericResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        GenericResponse<?> responseJson = new GenericResponse<>();
        responseJson.setStatus(ResponseStatus.FAILED.getStatus());
        responseJson.setCode(ErrorCode.ERROR_ACCESS_DENIED.getCode());
        responseJson.setMessage(ErrorCode.ERROR_ACCESS_DENIED.getMessage());
        responseJson.setException(accessDeniedException.getMessage());

        Gson gson = new Gson();
        String responseStr = gson.toJson(responseJson);

        try (PrintWriter writer = response.getWriter()) {
            writer.write(responseStr);
            writer.flush();
        }
    }
}
