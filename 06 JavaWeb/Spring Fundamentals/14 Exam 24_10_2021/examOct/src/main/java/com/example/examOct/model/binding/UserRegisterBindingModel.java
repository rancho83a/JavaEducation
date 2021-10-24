package com.example.examOct.model.binding;


import com.example.examOct.model.validator.UniqueUserName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {
    private String email;
    private String fullName;
    private String password;
    private String confirmPassword;
    private String username;

    public UserRegisterBindingModel() {
    }

    @NotBlank
    @Size(min = 3, max = 10, message = "Username length must be between 3 and 10 characters.")
    @UniqueUserName(message = "Username is occupied")
    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotNull
    @Size(min = 5, max = 20, message = "FullName length must be between 5 and 20 characters.")
    public String getFullName() {
        return fullName;
    }

    public UserRegisterBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @NotNull
    @Email(message = "Enter valid email")
    //@Pattern(regexp = "^(\\w+@\\w+)(.\\w+){2,}$")
    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    @NotNull(message = "Password length must be more than 3 characters.")
    @Size(min = 3, message = "Password length must be more than 3 characters.")
    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @NotNull(message = "Password length must be more than 3 characters.")
    @Size(min = 3, message = "Password length must be more than 3 characters.")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }


}
