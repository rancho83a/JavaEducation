package com.example.advquerying.models.dto;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ManagerDto extends BasicEmployeeDto{
    @Expose
    private List<EmployeeDto> subOrdinates;

    public List<EmployeeDto> getSubOrdinates() {
        return subOrdinates;
    }

    public void setSubOrdinates(List<EmployeeDto> subOrdinates) {
        this.subOrdinates = subOrdinates;
    }
}
