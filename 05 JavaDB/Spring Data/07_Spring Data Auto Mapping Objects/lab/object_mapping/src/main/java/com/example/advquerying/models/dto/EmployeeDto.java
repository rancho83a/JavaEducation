package com.example.advquerying.models.dto;

import java.math.BigDecimal;

public class EmployeeDto extends BasicEmployeeDto {

    private BigDecimal income;
    //    public EmployeeDto() {
//    }

//    public EmployeeDto(String firstName, String lastName, BigDecimal salary) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.salary = salary;
//    }


//
//    public static EmployeeDto ofEmployee(Employee employee){
//        return new EmployeeDto(employee.getFirstName(), employee.getLastName(), employee.getSalary());
//    }


    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
}
