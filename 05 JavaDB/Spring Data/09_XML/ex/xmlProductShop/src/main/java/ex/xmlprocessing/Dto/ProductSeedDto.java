package ex.xmlprocessing.Dto;



import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name="product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedDto {

    @XmlElement(name="name")
    private String name;
    @XmlElement(name="price")

    private BigDecimal price;

    public ProductSeedDto() {
    }

    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Size(min=3)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
