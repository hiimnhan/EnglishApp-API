package com.group1.EnglishApp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Hai Dang
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProgressDto {
    String topicName;
    String levelName;
    String noProgress;
    String progress;

    public String getNoProgress() {
        return noProgress;
    }

    public void setNoProgress(String noProgress) {
        this.noProgress = noProgress;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
