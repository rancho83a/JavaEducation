package jsoprocessing.ex.model.Dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerRootSeedDto {
    @XmlElement(name="customer")
    List<CustomerSeedDto> customers;

    public CustomerRootSeedDto() {
    }

    public List<CustomerSeedDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerSeedDto> customers) {
        this.customers = customers;
    }
}
