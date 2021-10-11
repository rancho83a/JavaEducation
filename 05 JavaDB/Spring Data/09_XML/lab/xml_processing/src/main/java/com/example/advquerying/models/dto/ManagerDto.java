package com.example.advquerying.models.dto;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="manager")
@XmlAccessorType(XmlAccessType.FIELD)
public class ManagerDto extends BasicEmployeeDto{
    @Expose
    @XmlElementWrapper(name="subordinates")
    @XmlElement(name="employee")
    private List<EmployeeDto> subOrdinates;

    public List<EmployeeDto> getSubOrdinates() {
        return subOrdinates;
    }

    public void setSubOrdinates(List<EmployeeDto> subOrdinates) {
        this.subOrdinates = subOrdinates;
    }
}
