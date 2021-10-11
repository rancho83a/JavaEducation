package jsoprocessing.ex.model.Dto.query5;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerWithCarRootDto {

    @XmlElement(name="customer")
    private  List<CustomerWithCarInfoDto> customers;

    public CustomerWithCarRootDto() {
    }

    public List<CustomerWithCarInfoDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerWithCarInfoDto> customers) {
        this.customers = customers;
    }
}
