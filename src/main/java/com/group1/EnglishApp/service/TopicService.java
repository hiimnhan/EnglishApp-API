package com.group1.EnglishApp.service;

import com.group1.EnglishApp.dto.LevelDto;
import com.group1.EnglishApp.dto.TopicDto;
import com.group1.EnglishApp.exception.EnglishAppValidationException;
import com.group1.EnglishApp.mapper.TopicMapper;
import com.group1.EnglishApp.model.Level;
import com.group1.EnglishApp.model.Topic;
import com.group1.EnglishApp.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hai Dang
 */
@Service
public class TopicService extends BaseService<Topic, Long> {
    private TopicRepository topicRepository;
    private TopicMapper topicMapper;

    @Autowired
    public TopicService(TopicRepository topicRepository, TopicMapper topicMapper){
        super(topicRepository);
        this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
    }
    public List<TopicDto> getAllTopic() throws EnglishAppValidationException {
        List<Topic> topicList = topicRepository.findAll();
        if(topicList.isEmpty()){
            throw new EnglishAppValidationException("Cannot load any topic");
        }
        return topicMapper.toDtoList(topicList);
    }
}
