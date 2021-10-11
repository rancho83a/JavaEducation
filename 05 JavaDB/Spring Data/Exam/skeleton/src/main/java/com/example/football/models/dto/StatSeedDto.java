package com.example.football.models.dto;

import javax.validation.constraints.DecimalMin;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class StatSeedDto {

    @XmlElement(name="passing")
    private float passing;
    @XmlElement(name="shooting")
    private float shooting;
    @XmlElement(name="endurance")
    private float endurance;

    public StatSeedDto() {
    }

    @DecimalMin(value = "0.001")
    public float getPassing() {
        return passing;
    }

    public void setPassing(float passing) {
        this.passing = passing;
    }
    @DecimalMin(value = "0.001")
    public float getShooting() {
        return shooting;
    }
    public void setShooting(float shooting) {
        this.shooting = shooting;
    }

    @DecimalMin(value = "0.001")
    public float getEndurance() {
        return endurance;
    }

    public void setEndurance(float endurance) {
        this.endurance = endurance;
    }
}
