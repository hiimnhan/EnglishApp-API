package com.group1.EnglishApp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Hai Dang
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserManagementDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private RoleDto role;
    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserManagementDto userDto = (UserManagementDto) o;

        return new EqualsBuilder()
                .append(id, userDto.id)
                .append(username, userDto.username)
                .append(firstName, userDto.firstName)
                .append(lastName, userDto.lastName)
                .append(role, userDto.role)
                .append(email, userDto.email)
                .append(active, userDto.active)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(username)
                .append(firstName)
                .append(lastName)
                .append(role)
                .append(email)
                .append(active)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("username", username)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("role", role)
                .append("email", email)
                .append("active", active)
                .toString();
    }
}
