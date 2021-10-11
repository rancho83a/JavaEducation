package jsoprocessing.ex.service.impl;

import com.google.gson.Gson;
import jsoprocessing.ex.constant.GlobalConstant;
import jsoprocessing.ex.model.Dto.CategorySeedDto;
import jsoprocessing.ex.model.Dto.viewDto.CategoryStatisticDto;
import jsoprocessing.ex.model.entity.Category;
import jsoprocessing.ex.model.entity.Product;
import jsoprocessing.ex.repository.CategoryRepository;
import jsoprocessing.ex.service.CategoryService;
import jsoprocessing.ex.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String CATEGORY_FILE_NAME = "categories.json";
    private final CategoryRepository categotyRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public CategoryServiceImpl(CategoryRepository categotyRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.categotyRepository = categotyRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCategories() throws IOException {
        if (categotyRepository.count() > 0) {
            return;
        }
        String categoryJson = Files.readString(Path.of(GlobalConstant.RESOURCE_FILE_PATH + CATEGORY_FILE_NAME));

        CategorySeedDto[] categorySeedDtos = gson.fromJson(categoryJson, CategorySeedDto[].class);
        Arrays.stream(categorySeedDtos)
                .filter(validationUtil::isValid)
                .map(d -> modelMapper.map(d, Category.class))
                .forEach(categotyRepository::save);

    }

    @Override
    public Set<Category> getRandomCategory() {
        Set<Category> categorySet = new HashSet<>();
        long categoryCount = this.categotyRepository.count();

        int randomCategoryCountSet = ThreadLocalRandom
                .current().nextInt(1, (int) categoryCount + 1);

        for (int i = 0; i < randomCategoryCountSet; i++) {
            Long randomId = ThreadLocalRandom.current().nextLong(1, categoryCount + 1);
            categorySet.add(this.categotyRepository.findById(randomId).orElse(null));
        }
        return categorySet;
    }

    @Override
    public List<CategoryStatisticDto> getAllCategoriesWithProducts() {


        return this.categotyRepository.findAllOrderByProductsCount()
                .stream()
                .map(category -> {
                    CategoryStatisticDto categoryStatisticDto = modelMapper.map(category, CategoryStatisticDto.class);
                    categoryStatisticDto.setCategory(category.getName());
                    int count = category.getProducts().size();

                    BigDecimal totalRevenue = category.getProducts().stream().map(Product::getPrice)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    BigDecimal averagePrice = BigDecimal.ZERO;
                    if (count > 0) {
                        averagePrice = totalRevenue.divide(BigDecimal.valueOf(count),  RoundingMode.FLOOR);
                    }
                    categoryStatisticDto.setProductsCount(count);
                    categoryStatisticDto.setAveragePrice(averagePrice);
                    categoryStatisticDto.setTotalRevenue(totalRevenue);

                    return categoryStatisticDto;
                })
                .collect(Collectors.toList());
    }
}
