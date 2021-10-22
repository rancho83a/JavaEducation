package com.example.examOct.service;


import com.example.examOct.model.entity.CategoryEntity;
import com.example.examOct.model.entity.CategoryNameEnum;

public interface CategoryService {
    void initializeCategory();

    CategoryEntity findCategoryByCategoryNameEnum(CategoryNameEnum categoryNameEnum);
}
