package com.example.examOct.service;


import com.example.examOct.model.entity.CategoryNameEnum;
import com.example.examOct.model.service.ProductServiceModel;
import com.example.examOct.model.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addProduct(ProductServiceModel productServiceModel);
    BigDecimal getTotalProductsSum();

    List<ProductServiceModel> getProductsByCategory(CategoryNameEnum categoryName);

    void buyProduct(Long id);

    void buyAll();
}
