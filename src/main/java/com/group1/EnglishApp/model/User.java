package com.group1.EnglishApp.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Nationalized;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * @author Hai Dang
 */
@Entity
@Table(name = "[User]", schema = "EnglishApp1")
public class User extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "FirstName")
    @Nationalized
    private String firstName;

    @Column(name = "LastName")
    @Nationalized
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "LastestLevelId", foreignKey = @ForeignKey(name = "FK_User_LastestLevelId"))
    private Level lastestLevelId;

    @Transient
    private String accessToken;

    @ManyToOne
    @JoinColumn(name = "RoleId", foreignKey = @ForeignKey(name = "FK_User_RoleId"))
    private Role role;

    @ManyToMany
    @JoinTable(name = "Progress", schema = "EnglishApp1",
            joinColumns = @JoinColumn(name = "UserId", foreignKey = @ForeignKey(name = "FK_Progress_UserId")),
            inverseJoinColumns = @JoinColumn(name = "WordId", foreignKey = @ForeignKey(name = "FK_Progress_WordId")))
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Word> words;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }

    public Level getLastestLevelId() {
        return lastestLevelId;
    }

    public void setLastestLevelId(Level lastestLevelId) {
        this.lastestLevelId = lastestLevelId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(username, user.username)
                .append(firstName, user.firstName)
                .append(lastName, user.lastName)
                .append(email, user.email)
                .append(lastestLevelId, user.lastestLevelId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(username)
                .append(firstName)
                .append(lastName)
                .append(email)
                .append(lastestLevelId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("username", username)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("email", email)
                .append("lastestLevelId", lastestLevelId)
                .toString();
    }
}
