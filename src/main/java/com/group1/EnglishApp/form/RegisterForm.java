package com.group1.EnglishApp.form;

import com.group1.EnglishApp.constraint.FieldMatch;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author Hai Dang
 */
@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields does not match")
@ApiModel
public class RegisterForm {
    @ApiModelProperty(required=true)
    @NotEmpty
    private String username;

    @ApiModelProperty(required=true)
    @NotEmpty
    private String password;

    @ApiModelProperty(required = true)
    @NotEmpty
    private String confirmPassword;

    @ApiModelProperty(required = true)
    @NotEmpty
    private String firstName;

    @ApiModelProperty(required = true)
    @NotEmpty
    private String lastName;

    @ApiModelProperty(required = true)
    @Email
    private String email;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
}
