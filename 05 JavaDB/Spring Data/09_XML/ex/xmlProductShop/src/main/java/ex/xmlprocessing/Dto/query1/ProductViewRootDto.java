package ex.xmlprocessing.Dto.query1;

import ex.xmlprocessing.Dto.ProductSeedDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

    @XmlRootElement(name="products")
    @XmlAccessorType(XmlAccessType.FIELD)
    public class ProductViewRootDto {
        public ProductViewRootDto() {
        }

        @XmlElement(name="product")
        List<ProductNamePriceSellerDto> products;


        public List<ProductNamePriceSellerDto> getProducts() {
            return products;
        }

        public void setProducts(List<ProductNamePriceSellerDto> products) {
            this.products = products;
        }

}
