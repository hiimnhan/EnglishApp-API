package com.group1.EnglishApp.controller;

import com.group1.EnglishApp.builder.ResponseEntityBuilder;
import com.group1.EnglishApp.constant.PathConstant;
import com.group1.EnglishApp.dto.ProgressDto;
import com.group1.EnglishApp.dto.UserDto;
import com.group1.EnglishApp.dto.WordDto;
import com.group1.EnglishApp.exception.EnglishAppValidationException;
import com.group1.EnglishApp.form.UserSearchForm;
import com.group1.EnglishApp.response.GenericResponse;
import com.group1.EnglishApp.service.WordService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author Hai Dang
 */
@Api(tags = "word management")
@RestController
@Validated
@RequestMapping(PathConstant.REST_API+PathConstant.WORD_CONTROLLER)
public class WordController {
    @Autowired
    WordService wordService;

    @ApiOperation(value = "Loading progress page - Show word in page", notes = "Show progress word of this user in page")
    @PreAuthorize("hasAuthority('ADMIN')"+"||hasAuthority('USER')")
    @RequestMapping(value = PathConstant.LIST, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<Page<WordDto>>> showLoadingProgressPageWord(Long userId, Long topicId, Long levelId) throws EnglishAppValidationException {

        return ResponseEntityBuilder.<Page<WordDto>>createBuilder()
                .data(wordService.getLoadingProgressPage(userId, topicId, levelId))
                .build();
    }

    @ApiOperation(value = "Back button API - Back button show Word in page", notes = "Back button show Word in page")
    @PreAuthorize("hasAuthority('ADMIN')"+"||hasAuthority('USER')")
    @RequestMapping(value = PathConstant.BACK_LIST, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<Page<WordDto>>> showBackPageWord(Long wordId) throws EnglishAppValidationException {

        return ResponseEntityBuilder.<Page<WordDto>>createBuilder()
                .data(wordService.getBackPageWord(wordId))
                .build();
    }

    @ApiOperation(value = "Next button API - Next button show Word in page", notes = "Next button show Word in page")
    @PreAuthorize("hasAuthority('ADMIN')"+"||hasAuthority('USER')")
    @RequestMapping(value = PathConstant.NEXT_LIST, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<Page<WordDto>>> showNextPageWord(Long userId, Long wordId) throws EnglishAppValidationException {

        return ResponseEntityBuilder.<Page<WordDto>>createBuilder()
                .data(wordService.getNextPageWord(userId, wordId))
                .build();
    }

    @ApiOperation(value = "Show Progress of this User", notes = "Show Progress of this User")
    @PreAuthorize("hasAuthority('ADMIN')"+"||hasAuthority('USER')")
    @RequestMapping(value = PathConstant.USER_PROGRESS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<List<ProgressDto>>> showUserProgress(Long userId) throws EnglishAppValidationException {

        return ResponseEntityBuilder.<List<ProgressDto>>createBuilder()
                .data(wordService.calculateProgress(userId))
                .build();
    }
}
