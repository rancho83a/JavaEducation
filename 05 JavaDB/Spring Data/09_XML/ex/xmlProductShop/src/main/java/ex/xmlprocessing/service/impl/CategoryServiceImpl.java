package ex.xmlprocessing.service.impl;

import ex.xmlprocessing.Dto.CategorySeedDto;
import ex.xmlprocessing.Dto.query3.CategoryRootDto;
import ex.xmlprocessing.Dto.query3.CategoryStatisticDto;
import ex.xmlprocessing.Dto.query3.ProductStatisticDto;
import ex.xmlprocessing.Repository.CategoryRepository;
import ex.xmlprocessing.model.entity.Category;
import ex.xmlprocessing.model.entity.Product;
import ex.xmlprocessing.service.CategoryService;
import ex.xmlprocessing.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCategories(List<CategorySeedDto> categories) {
        categories.stream()
                .filter(validationUtil::isValid)
                .map(categorySeedDto -> modelMapper.map(categorySeedDto, Category.class))
                .forEach(categoryRepository::save);
    }

    @Override
    public long getCategoryCount() {
        return this.categoryRepository.count();
    }

    @Override
    public Set<Category> getRandomCategory() {
        Set<Category> categorySet = new HashSet<>();
        long categoryCount = this.categoryRepository.count();

        int randomCategoryCountSet = ThreadLocalRandom
                .current().nextInt(1, (int) categoryCount + 1);

        for (int i = 0; i < randomCategoryCountSet; i++) {
            //  for (int i = 0; i < 3; i++) {
            Long randomId = ThreadLocalRandom.current().nextLong(1, categoryCount + 1);
            categorySet.add(this.categoryRepository.findById(randomId).orElse(null));
        }
        return categorySet;
    }

    @Override
    public CategoryRootDto getAllCategoryOrderByProductsCount() {
        CategoryRootDto categoryRootDto = new CategoryRootDto();
        categoryRootDto.setCategories(
                this.categoryRepository.findAllOrderByProductsCount()
                        .stream()
                        .map(category -> {
                            CategoryStatisticDto categoryStatisticDto = new CategoryStatisticDto();

                            categoryStatisticDto = modelMapper.map(category, CategoryStatisticDto.class);
                            ProductStatisticDto productStatisticDto = new ProductStatisticDto();

                            productStatisticDto.setProductsCount(category.getProducts().size());

                            productStatisticDto.setAveragePrice(
                                    category.getProducts().stream()
                                    .mapToDouble(p->p.getPrice().doubleValue())
                            .average()
                            .orElse(0));

                            productStatisticDto.setTotalRevenue(
                                    category.getProducts().stream()
                                    .mapToDouble(p->p.getPrice().doubleValue())
                                    .sum()
                            );

                            categoryStatisticDto.setProductStatisticDto(productStatisticDto);
                            return categoryStatisticDto;
                        })
                        .collect(Collectors.toList())
        );

        return categoryRootDto;
    }
}
