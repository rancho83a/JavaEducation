package com.example.coffeeshop.service;

import com.example.coffeeshop.model.entity.CategoryEntity;
import com.example.coffeeshop.model.entity.CategoryNameEnum;

public interface CategoryService {
    void initializeCategory();

    CategoryEntity findCategoryByCategoryNameEnum(CategoryNameEnum categoryNameEnum);
}
