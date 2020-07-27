package com.group1.EnglishApp.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Hai Dang
 */
@ApiModel
public class UserUpdateForm {
    @ApiModelProperty(required=true)
    @NotNull
    private Long id;
    @ApiModelProperty()
    @NotEmpty
    private String firstName;

    @ApiModelProperty()
    @NotEmpty
    private String lastName;

    @ApiModelProperty()
    @Email
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
