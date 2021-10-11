package jsoprocessing.ex.model.Dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierRootSeedDto {
    @XmlElement(name="supplier")
    List<SupplierSeedDto> suppliers;

    public SupplierRootSeedDto() {
    }

    public List<SupplierSeedDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierSeedDto> suppliers) {
        this.suppliers = suppliers;
    }
}
