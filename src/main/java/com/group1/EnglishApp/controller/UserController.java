package com.group1.EnglishApp.controller;

import com.group1.EnglishApp.builder.ResponseEntityBuilder;
import com.group1.EnglishApp.constant.PathConstant;
import com.group1.EnglishApp.dto.ProcessProgressDto;
import com.group1.EnglishApp.dto.UserDto;
import com.group1.EnglishApp.exception.EnglishAppException;
import com.group1.EnglishApp.exception.EnglishAppValidationException;
import com.group1.EnglishApp.form.ProcessProgressForm;
import com.group1.EnglishApp.form.RegisterForm;
import com.group1.EnglishApp.form.UserSearchForm;
import com.group1.EnglishApp.response.GenericResponse;
import com.group1.EnglishApp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * @author Hai Dang
 */
@Api(tags = "user management")
@RestController
@Validated
@RequestMapping(PathConstant.REST_API+PathConstant.USER_CONTROLLER)
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "Register - Register an user", notes = "Register an user and return some info")
    @ApiImplicitParam(name = "access_token", required = false, paramType = "path")
    @RequestMapping(value = PathConstant.REGISTER, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<UserDto>> register(@Valid @RequestBody RegisterForm registerForm) throws EnglishAppValidationException {

        return ResponseEntityBuilder.<UserDto>createBuilder()
                .data(userService.registerUser(registerForm))
                .build();
    }

    @ApiOperation(value = "Show - Show all user in page", notes = "Show all user in page and return some info")
    @PreAuthorize("hasAuthority('ADMIN')"+"||hasAuthority('USER')")
    @RequestMapping(value = PathConstant.LIST, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "Integer", paramType = "query", value = "Result page you want to retrive (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "Integer", paramType = "query", value = "Number of element you want to retrive per page.", defaultValue = "10"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "String", paramType = "query", value = "Sorting criteria in the format : property,property2,..,asc|desc. " +
                    "Default sort order is ascending. " + "Multiple sort criterias are supported.")
    })
    public ResponseEntity<GenericResponse<Page<UserDto>>> showPageUser(@ApiIgnore @PageableDefault(size = 1) Pageable pageable, UserSearchForm userSearchForm) throws EnglishAppValidationException {

        return ResponseEntityBuilder.<Page<UserDto>>createBuilder()
                .data(userService.getPageUser(pageable, userSearchForm))
                .build();
    }

    @ApiOperation(value = "Save Progress - Save Progress of this user", notes = "Save Progress of this user and return status")
    @PreAuthorize("hasAuthority('ADMIN')"+"||hasAuthority('USER')")
    @RequestMapping(value = PathConstant.PROCESS_PROGRESS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<ProcessProgressDto>> register(@Valid @RequestBody ProcessProgressForm processProgressForm) throws EnglishAppValidationException {

        return ResponseEntityBuilder.<ProcessProgressDto>createBuilder()
                .data(userService.processProgress(processProgressForm))
                .build();
    }

    @ApiOperation(value = "Save Progress - Save Progress of this user", notes = "Save Progress of this user and return status")
    @PreAuthorize("hasAuthority('ADMIN')"+"||hasAuthority('USER')")
    @RequestMapping(value = PathConstant.SET_LEVEL, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<UserDto>> setLevel(Long userId, Long levelId) throws EnglishAppValidationException {

        return ResponseEntityBuilder.<UserDto>createBuilder()
                .data(userService.setLevel(userId, levelId))
                .build();
    }
}
