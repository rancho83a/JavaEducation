package ex.xmlprocessing.Dto.query4;

import javax.xml.bind.annotation.*;
import java.util.List;


@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class UserFirstLastNameAgeDto {
    @XmlAttribute(name="first-name")
    private String firstName;
    @XmlAttribute(name="last-name")
    private String lastName;
    @XmlAttribute(name="age")
    private String age;
    @XmlElement(name="sold-product")
    private SoldProductsDto soldProducts;

    public UserFirstLastNameAgeDto() {
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public SoldProductsDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(SoldProductsDto soldProducts) {
        this.soldProducts = soldProducts;
    }
}
