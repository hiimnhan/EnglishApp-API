package com.group1.EnglishApp.dto;

import java.util.List;

public class LevelAndTopicToDropBoxDto {
    private List<TopicDto> topicDtoList;
    private List<LevelDto> levelDtoList;

    public List<TopicDto> getTopicDtoList() {
        return topicDtoList;
    }

    public void setTopicDtoList(List<TopicDto> topicDtoList) {
        this.topicDtoList = topicDtoList;
    }

    public List<LevelDto> getLevelDtoList() {
        return levelDtoList;
    }

    public void setLevelDtoList(List<LevelDto> levelDtoList) {
        this.levelDtoList = levelDtoList;
    }
}
