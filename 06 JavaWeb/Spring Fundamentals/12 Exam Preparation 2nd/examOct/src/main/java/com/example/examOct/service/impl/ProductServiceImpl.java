package com.example.examOct.service.impl;

import com.example.examOct.model.entity.ProductEntity;
import com.example.examOct.model.service.ProductServiceModel;
import com.example.examOct.repository.ProductRepository;
import com.example.examOct.service.CategoryService;
import com.example.examOct.service.ProductService;
import com.example.examOct.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ModelMapper modelMapper, UserService userService, CategoryService categoryService, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {

        ProductEntity product = modelMapper.map(productServiceModel, ProductEntity.class);
        product.setCategory(categoryService.findCategoryByCategoryNameEnum(productServiceModel.getCategory()));
        this.productRepository.save(product);


    }
}
