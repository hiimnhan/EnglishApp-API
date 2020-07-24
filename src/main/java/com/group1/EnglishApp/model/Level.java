package com.group1.EnglishApp.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Hai Dang
 */
@Entity
@Table(name = "[Level]", schema = "EnglishApp1")
public class Level extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @Column(name = "Name")
    private String name;

    @OneToMany(targetEntity = Word.class, mappedBy = "levelOfWord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Word> words;

    @OneToMany(targetEntity = Question.class, mappedBy = "levelOfQuestion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Question> questions;

    @OneToMany(targetEntity = User.class, mappedBy = "lastestLevelId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
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

        Level level = (Level) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(name, level.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("name", name)
                .toString();
    }
}
