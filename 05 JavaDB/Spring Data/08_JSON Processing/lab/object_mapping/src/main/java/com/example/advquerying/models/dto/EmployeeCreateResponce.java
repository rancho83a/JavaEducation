package com.example.advquerying.models.dto;

import com.google.gson.annotations.Expose;

public class EmployeeCreateResponce extends EmployeeCreateRequest{
    @Expose
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
