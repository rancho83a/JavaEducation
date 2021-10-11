package jsoprocessing.ex.model.Dto.query6;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.List;

@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleInfoDto {
    @XmlElement(name = "car")
    private CarMakeModelDistanceDto car;
    @XmlElement(name="customer-name")
    private String customerName;
    @XmlElement(name="discount")
    private Double discount;

    @XmlElement(name="price")
    private BigDecimal price;
    @XmlElement(name="price-with-discount")
    private BigDecimal priceWithDiscount;

    public SaleInfoDto() {
    }

    public CarMakeModelDistanceDto getCars() {
        return car;
    }


    public void setCar(CarMakeModelDistanceDto car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
