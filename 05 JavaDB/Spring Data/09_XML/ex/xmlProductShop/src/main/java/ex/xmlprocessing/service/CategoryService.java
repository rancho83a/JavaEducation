package ex.xmlprocessing.service;


import ex.xmlprocessing.Dto.CategorySeedDto;
import ex.xmlprocessing.Dto.query3.CategoryRootDto;
import ex.xmlprocessing.model.entity.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategories(List<CategorySeedDto> categories);
    long getCategoryCount();

    Set<Category> getRandomCategory();

    CategoryRootDto getAllCategoryOrderByProductsCount();
}
