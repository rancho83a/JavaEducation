package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.entity.CategoryEntity;
import com.example.coffeeshop.model.entity.CategoryNameEnum;
import com.example.coffeeshop.repository.CategoryRepository;
import com.example.coffeeshop.service.CategoryService;
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
                        switch (categoryNameEnum) {
                            case CAKE:
                                categoryEntity.setNeededTime(10);
                                break;
                            case COFFEE:
                                categoryEntity.setNeededTime(2);
                                break;
                            case DRINK:
                                categoryEntity.setNeededTime(1);
                                break;
                            case OTHER:
                                categoryEntity.setNeededTime(5);
                        }
                        categoryRepository.save(categoryEntity);
                    });
        }
    }
}
