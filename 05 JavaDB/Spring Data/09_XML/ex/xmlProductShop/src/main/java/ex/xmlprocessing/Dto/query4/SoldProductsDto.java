package ex.xmlprocessing.Dto.query4;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductsDto {
    @XmlAttribute
    private String count;
    @XmlElement(name="product")
    private List<ProductDto> products;

    public SoldProductsDto() {
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<ex.xmlprocessing.Dto.query4.ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ex.xmlprocessing.Dto.query4.ProductDto> products) {
        this.products = products;
    }
}
