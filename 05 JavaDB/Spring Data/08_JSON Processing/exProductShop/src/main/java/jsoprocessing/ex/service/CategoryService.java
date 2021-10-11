package jsoprocessing.ex.service;

import jsoprocessing.ex.model.Dto.viewDto.CategoryStatisticDto;
import jsoprocessing.ex.model.entity.Category;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;
    Set<Category> getRandomCategory();

    List<CategoryStatisticDto> getAllCategoriesWithProducts();

}
