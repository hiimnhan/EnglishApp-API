package com.group1.EnglishApp.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Hai Dang
 */
@Entity
@Table(name = "[Word]", schema = "EnglishApp")
public class Word extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @Column(name = "Vocabulary")
    @Nationalized
    private String vocabulary;

    @Column(name = "Spell")
    @Nationalized
    private String spell;

    @Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "TopicId", foreignKey = @ForeignKey(name = "FK_Word_TopicId"))
    private Topic topicOfWord;

    @ManyToOne
    @JoinColumn(name = "LevelId", foreignKey = @ForeignKey(name = "FK_Word_LevelId"))
    private Level levelOfWord;

    @OneToMany(targetEntity = Question.class, mappedBy = "wordAnswer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Question> questions;

    @ManyToMany
    @JoinTable(name = "Progress", schema = "EnglishApp",
            joinColumns = @JoinColumn(name = "WordId", foreignKey = @ForeignKey(name = "FK_Progress_WordId")),
            inverseJoinColumns = @JoinColumn(name = "UserId", foreignKey = @ForeignKey(name = "FK_Progress_UserId")))
    private Set<User> users;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Topic getTopicOfWord() {
        return topicOfWord;
    }

    public void setTopicOfWord(Topic topicOfWord) {
        this.topicOfWord = topicOfWord;
    }

    public Level getLevelOfWord() {
        return levelOfWord;
    }

    public void setLevelOfWord(Level levelOfWord) {
        this.levelOfWord = levelOfWord;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(vocabulary, word.vocabulary)
                .append(spell, word.spell)
                .append(image, word.image)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(vocabulary)
                .append(spell)
                .append(image)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("vocabulary", vocabulary)
                .append("spell", spell)
                .append("image", image)
                .toString();
    }
}
