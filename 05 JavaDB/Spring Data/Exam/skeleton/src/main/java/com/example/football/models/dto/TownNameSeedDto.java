package com.example.football.models.dto;

import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="player")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownNameSeedDto {
    @XmlElement(name="name")
    private String name;

    public TownNameSeedDto() {
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
