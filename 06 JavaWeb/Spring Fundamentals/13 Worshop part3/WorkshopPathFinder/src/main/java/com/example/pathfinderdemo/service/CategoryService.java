package com.example.pathfinderdemo.service;


import com.example.pathfinderdemo.model.entity.CategoryEntity;
import com.example.pathfinderdemo.model.entity.enums.CategoryEnum;

public interface CategoryService {
    CategoryEntity findCategoryByCategoryName(CategoryEnum categoryEnum);
}
