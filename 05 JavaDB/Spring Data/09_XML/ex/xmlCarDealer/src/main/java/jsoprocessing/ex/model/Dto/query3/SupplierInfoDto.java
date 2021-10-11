package jsoprocessing.ex.model.Dto.query3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "suplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierInfoDto {
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlAttribute(name="parts-count")
    private String partsCount;

    public SupplierInfoDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(String partsCount) {
        this.partsCount = partsCount;
    }
}
