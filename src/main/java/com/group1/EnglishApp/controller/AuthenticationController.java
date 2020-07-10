package com.group1.EnglishApp.controller;

import com.group1.EnglishApp.builder.ResponseEntityBuilder;
import com.group1.EnglishApp.constant.PathConstant;
import com.group1.EnglishApp.dto.UserDto;
import com.group1.EnglishApp.exception.EnglishAppException;
import com.group1.EnglishApp.form.LoginForm;
import com.group1.EnglishApp.response.GenericResponse;
import com.group1.EnglishApp.service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hai Dang
 */
@Api(tags = "authentication")
@RestController
@Validated
@RequestMapping(PathConstant.REST_API)
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @ApiOperation(value = "Login - Login an user", notes = "Login an user and return access token")
    @ApiImplicitParam(name = "access_token", required = false, paramType = "path")
    @RequestMapping(value = PathConstant.LOGIN, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<UserDto>> login(HttpServletRequest httpRequest,
                                                          @Validated @RequestBody LoginForm loginForm) throws EnglishAppException {

        return ResponseEntityBuilder.<UserDto>createBuilder()
                .data(authenticationService.processLogin(loginForm, new WebAuthenticationDetails(httpRequest)))
                .build();
    }
}
