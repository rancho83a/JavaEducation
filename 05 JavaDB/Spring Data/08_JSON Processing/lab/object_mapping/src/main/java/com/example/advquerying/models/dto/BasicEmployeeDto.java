package com.example.advquerying.models.dto;

import com.google.gson.annotations.Expose;

public class BasicEmployeeDto {

    private String firstName;

    private String lastName;


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
}
