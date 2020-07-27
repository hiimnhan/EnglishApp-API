package com.group1.EnglishApp.mapper;

import com.group1.EnglishApp.dto.TopicDto;
import com.group1.EnglishApp.model.Topic;
import org.mapstruct.Mapper;

/**
 * @author Hai Dang
 */
@Mapper(componentModel = "spring")
public abstract class TopicMapper implements AbstractMapper<Topic, TopicDto> {
}
