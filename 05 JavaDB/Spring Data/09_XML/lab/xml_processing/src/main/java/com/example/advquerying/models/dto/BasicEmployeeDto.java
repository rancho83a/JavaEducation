package com.example.advquerying.models.dto;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlTransient
public class BasicEmployeeDto {

    @XmlAttribute(name="first_name")
    private String firstName;
    @XmlAttribute(name="last_name")
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
