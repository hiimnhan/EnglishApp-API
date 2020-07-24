package com.group1.EnglishApp.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Hai Dang
 */
@ApiModel
public class WordUpdateForm {
    @ApiModelProperty(required=true)
    @NotNull
    private Long id;
    @ApiModelProperty
    private String vocabulary;
    @ApiModelProperty
    private String spell;
    @ApiModelProperty
    private String translateVi;
    @ApiModelProperty(required=false)
    private String image;
    @ApiModelProperty
    private Long levelId;
    @ApiModelProperty
    private Long topicId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getTranslateVi() {
        return translateVi;
    }

    public void setTranslateVi(String translateVi) {
        this.translateVi = translateVi;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }
}
