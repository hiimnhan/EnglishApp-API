package com.group1.EnglishApp.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * @author Hai Dang
 */
@ApiModel
public class LoginForm {
    @ApiModelProperty(required=true)
    @NotEmpty
    private String username;

    @ApiModelProperty(required=true)
    @NotEmpty
    private String password;

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
}
