package jsoprocessing.ex.model.Dto.query3;

import jsoprocessing.ex.model.Dto.query2.CarInfoDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierRootDto {

    @XmlElement(name="suplier")
    private List<SupplierInfoDto> suppliers;

    public SupplierRootDto() {
    }

    public List<SupplierInfoDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierInfoDto> suppliers) {
        this.suppliers = suppliers;
    }
}

