package ex.xmlprocessing.Dto.query2;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserFirstLastNameProductsDto {
    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlElement(name="product")
    @XmlElementWrapper(name="sold-products")
    private List<ProductNamePriceBuyerDto> soldProducts;

    public UserFirstLastNameProductsDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ProductNamePriceBuyerDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductNamePriceBuyerDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
