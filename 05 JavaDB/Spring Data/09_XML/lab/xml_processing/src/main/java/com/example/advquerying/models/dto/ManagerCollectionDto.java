package com.example.advquerying.models.dto;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="managers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ManagerCollectionDto {

    @Expose
    @XmlElement(name="manager")
    private List<ManagerDto> managers;

    public ManagerCollectionDto() {
    }

    public ManagerCollectionDto(List<ManagerDto> managers) {
        this.managers = managers;
    }

    public List<ManagerDto> getManagers() {
        return managers;
    }

    public void setManagers(List<ManagerDto> managers) {
        this.managers = managers;
    }
}

