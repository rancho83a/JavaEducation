package com.example.advquerying.models.dto;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"firstName", "income", "lastName"})
public class EmployeeDto extends BasicEmployeeDto {


    @SerializedName("salary")
@XmlAttribute
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
