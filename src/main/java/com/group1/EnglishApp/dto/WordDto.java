package com.group1.EnglishApp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.group1.EnglishApp.model.Level;
import com.group1.EnglishApp.model.Topic;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Hai Dang
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WordDto {
    private Long id;
    private String vocabulary;
    private String spell;
    private String translateVi;
    private String image;
    private TopicDto topicOfWord;
    private LevelDto levelOfWord;

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

    public TopicDto getTopicOfWord() {
        return topicOfWord;
    }

    public void setTopicOfWord(TopicDto topicOfWord) {
        this.topicOfWord = topicOfWord;
    }

    public LevelDto getLevelOfWord() {
        return levelOfWord;
    }

    public void setLevelOfWord(LevelDto levelOfWord) {
        this.levelOfWord = levelOfWord;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        WordDto wordDto = (WordDto) o;

        return new EqualsBuilder()
                .append(id, wordDto.id)
                .append(vocabulary, wordDto.vocabulary)
                .append(spell, wordDto.spell)
                .append(translateVi, wordDto.translateVi)
                .append(image, wordDto.image)
                .append(topicOfWord, wordDto.topicOfWord)
                .append(levelOfWord, wordDto.levelOfWord)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(vocabulary)
                .append(spell)
                .append(translateVi)
                .append(image)
                .append(topicOfWord)
                .append(levelOfWord)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("vocabulary", vocabulary)
                .append("spell", spell)
                .append("translateVi", translateVi)
                .append("image", image)
                .append("topicOfWord", topicOfWord)
                .append("levelOfWord", levelOfWord)
                .toString();
    }
}
