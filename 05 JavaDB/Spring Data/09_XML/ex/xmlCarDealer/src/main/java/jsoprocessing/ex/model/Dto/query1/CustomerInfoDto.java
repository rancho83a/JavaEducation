package jsoprocessing.ex.model.Dto.query1;

import jsoprocessing.ex.util.LocalDateTimeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlRootElement(name="customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerInfoDto {
    private Long id;
    private String name;

    @XmlElement(name="birth-date")
    //@XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    private String birthDate;

    @XmlElement(name="is-young-driver")
    private boolean isYoungDriver;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
