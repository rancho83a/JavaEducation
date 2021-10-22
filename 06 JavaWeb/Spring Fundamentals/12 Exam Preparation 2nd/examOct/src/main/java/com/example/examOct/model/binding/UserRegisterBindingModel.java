package com.example.examOct.model.binding;


import com.example.examOct.model.validator.UniqueUserName;

import javax.validation.constraints.*;

public class UserRegisterBindingModel {
    private String username;

    private String email;

    private String password;
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    @NotBlank(message = "Email can not be empty")
     @Email(message = "email must contains @")
    //@Pattern(regexp = "^(\\w+@\\w+)(.\\w+){2,}$")
    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }


    @NotNull
    @Size(min = 3, max = 20,  message = "Password must be between 3 and 20 characters")
    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @NotNull
    @Size(min = 3, max =20, message = "Please, confirm password")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    @NotNull
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    @UniqueUserName(message = "Username is occupied")
    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
