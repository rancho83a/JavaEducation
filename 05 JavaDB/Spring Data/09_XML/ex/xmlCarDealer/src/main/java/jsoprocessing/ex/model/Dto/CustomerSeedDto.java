package jsoprocessing.ex.model.Dto;

import jsoprocessing.ex.util.LocalDateTimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerSeedDto {
    @XmlAttribute(name = "name")
    private String name;

    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    @XmlElement(name="birth-date")
    private LocalDateTime birthDate;

    @XmlElement(name="is-young-driver")
    private Boolean isYoungDriver;

    public CustomerSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
