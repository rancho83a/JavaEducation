package project.nextleveltechnology.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="company")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanySeedDto {
    @XmlAttribute(name="name")
    private String name;

    public CompanySeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
