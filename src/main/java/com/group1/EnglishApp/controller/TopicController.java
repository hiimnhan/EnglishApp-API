package com.group1.EnglishApp.controller;

import com.group1.EnglishApp.builder.ResponseEntityBuilder;
import com.group1.EnglishApp.constant.PathConstant;
import com.group1.EnglishApp.dto.LevelDto;
import com.group1.EnglishApp.dto.TopicDto;
import com.group1.EnglishApp.exception.EnglishAppValidationException;
import com.group1.EnglishApp.response.GenericResponse;
import com.group1.EnglishApp.service.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Hai Dang
 */
@Api(tags = "topic management")
@RestController
@Validated
@RequestMapping(PathConstant.REST_API+PathConstant.TOPIC_CONTROLLER)
public class TopicController {
    @Autowired
    TopicService topicService;

    @ApiOperation(value = "Loading all topic", notes = "Loading list of all topic")
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = PathConstant.LIST, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<List<TopicDto>>> getAllTopic() throws EnglishAppValidationException {

        return ResponseEntityBuilder.<List<TopicDto>>createBuilder()
                .data(topicService.getAllTopic())
                .build();
    }
}
