package com.example.examOct.service.impl;

import com.example.examOct.model.entity.CategoryEntity;
import com.example.examOct.model.entity.CategoryNameEnum;
import com.example.examOct.repository.CategoryRepository;
import com.example.examOct.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepositories) {
        this.categoryRepository = categoryRepositories;
    }

    @Override
    public void initializeCategory() {
        if (this.categoryRepository.count() == 0) {
            Arrays.stream(CategoryNameEnum.values())
                    .forEach(categoryNameEnum -> {
                        CategoryEntity categoryEntity = new CategoryEntity();
                        categoryEntity.setName(categoryNameEnum);
                        categoryEntity.setDescription("this category is: "+ categoryNameEnum.name());
                        categoryRepository.save(categoryEntity);
                    });
        }
    }

    @Override
    public CategoryEntity findCategoryByCategoryNameEnum(CategoryNameEnum categoryNameEnum) {
        return this.categoryRepository.findByName(categoryNameEnum).orElse(null);
    }
}
