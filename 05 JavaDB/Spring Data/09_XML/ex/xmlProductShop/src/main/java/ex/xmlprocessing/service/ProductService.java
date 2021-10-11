package ex.xmlprocessing.service;

import ex.xmlprocessing.Dto.CategorySeedDto;
import ex.xmlprocessing.Dto.ProductSeedDto;
import ex.xmlprocessing.Dto.query1.ProductNamePriceSellerDto;
import ex.xmlprocessing.Dto.query1.ProductViewRootDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    long getProductCount();

    void seedProducts(List<ProductSeedDto> products);

    ProductViewRootDto getAllProductInRange(BigDecimal low, BigDecimal up);
}
