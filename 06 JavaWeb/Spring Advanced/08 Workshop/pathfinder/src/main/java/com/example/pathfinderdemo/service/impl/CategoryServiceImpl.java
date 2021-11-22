package com.example.pathfinderdemo.service.impl;

import com.example.pathfinderdemo.Repository.CategoryRepository;
import com.example.pathfinderdemo.model.entity.CategoryEntity;
import com.example.pathfinderdemo.model.entity.enums.CategoryEnum;
import com.example.pathfinderdemo.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryEntity findCategoryByCategoryName(CategoryEnum categoryEnum) {
        return this.categoryRepository.findByName(categoryEnum).orElse(null);
    }
}
