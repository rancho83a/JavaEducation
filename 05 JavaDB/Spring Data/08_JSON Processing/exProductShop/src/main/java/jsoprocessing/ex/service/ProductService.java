package jsoprocessing.ex.service;

import jsoprocessing.ex.model.Dto.viewDto.ProductNamePriceSellerDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProducts() throws IOException;

    List<ProductNamePriceSellerDto> getAllProductsInRangeWithoutByuerOrderByPrice(BigDecimal low, BigDecimal up);
}
