package com.group1.EnglishApp.controller;

import com.group1.EnglishApp.builder.ResponseEntityBuilder;
import com.group1.EnglishApp.constant.PathConstant;
import com.group1.EnglishApp.dto.LevelAndTopicToDropBoxDto;
import com.group1.EnglishApp.dto.ProgressDto;
import com.group1.EnglishApp.dto.UserDto;
import com.group1.EnglishApp.dto.WordDto;
import com.group1.EnglishApp.exception.EnglishAppValidationException;
import com.group1.EnglishApp.form.UserSearchForm;
import com.group1.EnglishApp.form.WordCreateForm;
import com.group1.EnglishApp.form.WordSearchForm;
import com.group1.EnglishApp.form.WordUpdateForm;
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

//    @ApiOperation(value = "Loading progress page - Show word in page", notes = "Show progress word of this user in page")
//    @PreAuthorize("hasAuthority('ADMIN')"+"||hasAuthority('USER')")
//    @RequestMapping(value = PathConstant.LIST, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<GenericResponse<Page<WordDto>>> showLoadingProgressPageWord(Long userId, Long topicId, Long levelId) throws EnglishAppValidationException {
//
//        return ResponseEntityBuilder.<Page<WordDto>>createBuilder()
//                .data(wordService.getLoadingProgressPage(userId, levelId, topicId))
//                .build();
//    }

    @ApiOperation(value = "Loading progress page - Show word in page", notes = "Show progress word of this user in page")
    @PreAuthorize("hasAuthority('ADMIN')"+"||hasAuthority('USER')")
    @RequestMapping(value = PathConstant.FIRST_WORD, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<Page<WordDto>>> getFirstPageWord(Long userId, Long topicId, Long levelId) throws EnglishAppValidationException {

        return ResponseEntityBuilder.<Page<WordDto>>createBuilder()
                .data(wordService.getFirstWord(userId, levelId, topicId))
                .build();
    }

//    @ApiOperation(value = "Back button API - Back button show Word in page", notes = "Back button show Word in page")
//    @PreAuthorize("hasAuthority('ADMIN')"+"||hasAuthority('USER')")
//    @RequestMapping(value = PathConstant.BACK_LIST, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<GenericResponse<Page<WordDto>>> showBackPageWord(Long wordId) throws EnglishAppValidationException {
//
//        return ResponseEntityBuilder.<Page<WordDto>>createBuilder()
//                .data(wordService.getBackPageWord(wordId))
//                .build();
//    }
//
//    @ApiOperation(value = "Next button API - Next button show Word in page", notes = "Next button show Word in page")
//    @PreAuthorize("hasAuthority('ADMIN')"+"||hasAuthority('USER')")
//    @RequestMapping(value = PathConstant.NEXT_LIST, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<GenericResponse<Page<WordDto>>> showNextPageWord(Long userId, Long wordId) throws EnglishAppValidationException {
//
//        return ResponseEntityBuilder.<Page<WordDto>>createBuilder()
//                .data(wordService.getNextPageWord(userId, wordId))
//                .build();
//    }

    @ApiOperation(value = "Process Page API - show Word in page", notes = "Show Word in page by request")
    @PreAuthorize("hasAuthority('ADMIN')"+"||hasAuthority('USER')")
    @RequestMapping(value = PathConstant.PROCESS_PAGE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "Integer", paramType = "query", value = "Result page you want to retrive (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "Integer", paramType = "query", value = "Number of element you want to retrive per page.", defaultValue = "10"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "String", paramType = "query", value = "Sorting criteria in the format : property,property2,..,asc|desc. " +
                    "Default sort order is ascending. " + "Multiple sort criterias are supported.")
    })
    public ResponseEntity<GenericResponse<Page<WordDto>>> processPageWord(@ApiIgnore @PageableDefault(size = 1) Pageable pageable,Long userId, Long wordId) throws EnglishAppValidationException {

        return ResponseEntityBuilder.<Page<WordDto>>createBuilder()
                .data(wordService.processPageWord(userId, wordId, pageable))
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

    @ApiOperation(value = "Get All Word - Get All Word in page", notes = "Get All Word in page")
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = PathConstant.GET_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "Integer", paramType = "query", value = "Result page you want to retrive (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "Integer", paramType = "query", value = "Number of element you want to retrive per page.", defaultValue = "10"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "String", paramType = "query", value = "Sorting criteria in the format : property,property2,..,asc|desc. " +
                    "Default sort order is ascending. " + "Multiple sort criterias are supported.")
    })
    public ResponseEntity<GenericResponse<Page<WordDto>>> processPageWord(@ApiIgnore Pageable pageable, WordSearchForm wordSearchForm) throws EnglishAppValidationException {

        return ResponseEntityBuilder.<Page<WordDto>>createBuilder()
                .data(wordService.getAllWord(pageable, wordSearchForm))
                .build();
    }

    @ApiOperation(value = "Get One Word", notes = "Get detail info of a Word")
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = PathConstant.DETAIL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<WordDto>> createNewWord(Long wordId) throws EnglishAppValidationException {

        return ResponseEntityBuilder.<WordDto>createBuilder()
                .data(wordService.getOneWord(wordId))
                .build();
    }

    @ApiOperation(value = "Create new Word", notes = "Create new word and return info")
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = PathConstant.CREATE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<WordDto>> createNewWord(WordCreateForm wordCreateForm) throws EnglishAppValidationException {

        return ResponseEntityBuilder.<WordDto>createBuilder()
                .data(wordService.createNewWord(wordCreateForm))
                .build();
    }

    @ApiOperation(value = "Update a Word", notes = "Update a word and return info")
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = PathConstant.UPDATE, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<WordDto>> updateWord(WordUpdateForm wordUpdateForm) throws EnglishAppValidationException {

        return ResponseEntityBuilder.<WordDto>createBuilder()
                .data(wordService.updateWord(wordUpdateForm))
                .build();
    }

    @ApiOperation(value = "Delete a Word", notes = "Delete a word and boolean status")
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = PathConstant.DELETE, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<Boolean>> updateWord(Long wordId) throws EnglishAppValidationException {

        return ResponseEntityBuilder.<Boolean>createBuilder()
                .data(wordService.deleteWord(wordId))
                .build();
    }
    @ApiOperation(value = "Get All Level and Topic", notes = "Get all level and topic to dropbox")
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = PathConstant.LEVEL_AND_TOPIC, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<LevelAndTopicToDropBoxDto>> getAllLevelAndTopic() throws EnglishAppValidationException {

        return ResponseEntityBuilder.<LevelAndTopicToDropBoxDto>createBuilder()
                .data(wordService.getAllTopicAndLevel())
                .build();
    }
}
