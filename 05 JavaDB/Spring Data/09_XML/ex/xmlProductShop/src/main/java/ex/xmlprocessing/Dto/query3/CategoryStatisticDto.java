package ex.xmlprocessing.Dto.query3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryStatisticDto {
    @XmlAttribute(name="name")
    private String name;
    private ProductStatisticDto productStatisticDto;

    public CategoryStatisticDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductStatisticDto getProductStatisticDto() {
        return productStatisticDto;
    }

    public void setProductStatisticDto(ProductStatisticDto productStatisticDto) {
        this.productStatisticDto = productStatisticDto;
    }
}
