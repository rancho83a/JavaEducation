package jsoprocessing.ex.model.Dto;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierSeedDto {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "is-importer")
    private Boolean isImporter;

    public SupplierSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getImporter() {
        return isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }
}
