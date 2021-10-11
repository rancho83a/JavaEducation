package jsoprocessing.ex.model.Dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartRootSeedDto {
    @XmlElement(name="part")
    List<PartSeedDto> parts;

    public PartRootSeedDto() {
    }

    public List<PartSeedDto> getParts() {
        return parts;
    }

    public void setParts(List<PartSeedDto> parts) {
        this.parts = parts;
    }
}
