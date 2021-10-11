package com.example.football.models.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="stat")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatIdSeedDto {
    private Long id;

    public StatIdSeedDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
