package com.group1.EnglishApp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Hai Dang
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessProgressDto {
    private Boolean processStatus;
    private String message;

    public Boolean getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Boolean processStatus) {
        this.processStatus = processStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
