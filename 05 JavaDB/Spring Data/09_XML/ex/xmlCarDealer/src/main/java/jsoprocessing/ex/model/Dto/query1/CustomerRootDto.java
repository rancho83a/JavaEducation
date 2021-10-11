package jsoprocessing.ex.model.Dto.query1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerRootDto {

    @XmlElement(name="customer")
    private  List<CustomerInfoDto> customers;

    public CustomerRootDto() {
    }

    public List<CustomerInfoDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerInfoDto> customers) {
        this.customers = customers;
    }
}
