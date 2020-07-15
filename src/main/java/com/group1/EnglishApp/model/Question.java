package com.group1.EnglishApp.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * @author Hai Dang
 */
@Entity
@Table(name = "[Question]", schema = "EnglishApp1")
public class Question extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @Column(name = "Question")
    private String question;

    @Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "TopicId", foreignKey = @ForeignKey(name = "FK_Question_TopicId"))
    private Topic topicOfQuestion;

    @ManyToOne
    @JoinColumn(name = "LevelId", foreignKey = @ForeignKey(name = "FK_Question_LevelId"))
    private Level levelOfQuestion;

    @ManyToOne
    @JoinColumn(name = "WordId", foreignKey = @ForeignKey(name = "FK_Question_WordId"))
    private Word wordAnswer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Topic getTopicOfQuestion() {
        return topicOfQuestion;
    }

    public void setTopicOfQuestion(Topic topicOfQuestion) {
        this.topicOfQuestion = topicOfQuestion;
    }

    public Level getLevelOfQuestion() {
        return levelOfQuestion;
    }

    public void setLevelOfQuestion(Level levelOfQuestion) {
        this.levelOfQuestion = levelOfQuestion;
    }

    public Word getWordAnswer() {
        return wordAnswer;
    }

    public void setWordAnswer(Word wordAnswer) {
        this.wordAnswer = wordAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(question, question.question)
                .append(image, question.image)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(question)
                .append(image)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("question", question)
                .append("image", image)
                .toString();
    }
}
