package com.group1.EnglishApp.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Hai Dang
 */
@ApiModel
public class ProcessProgressForm {
    @ApiModelProperty(required=true)
    @NotNull
    private Long userId;
    @ApiModelProperty(required=true)
    @NotNull
    private Long wordId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }
}
