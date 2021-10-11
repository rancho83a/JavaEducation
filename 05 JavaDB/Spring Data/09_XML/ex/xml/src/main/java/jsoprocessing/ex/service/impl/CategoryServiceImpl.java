package jsoprocessing.ex.service.impl;

import com.google.gson.Gson;
import jsoprocessing.ex.constant.GlobalConstant;
import jsoprocessing.ex.model.Dto.CategorySeedDto;
import jsoprocessing.ex.model.entity.Category;
import jsoprocessing.ex.repository.CategotyRepository;
import jsoprocessing.ex.service.CategoryService;
import jsoprocessing.ex.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String CATEGORY_FILE_NAME="categories.json";
    private final CategotyRepository categotyRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public CategoryServiceImpl(CategotyRepository categotyRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.categotyRepository = categotyRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCategories() throws IOException {
        if(categotyRepository.count()>0){
            return;
        }
        String categoryJson = Files.readString(Path.of(GlobalConstant.RESOURCE_FILE_PATH + CATEGORY_FILE_NAME));

        CategorySeedDto[] categorySeedDtos = gson.fromJson(categoryJson, CategorySeedDto[].class);
        Arrays.stream(categorySeedDtos)
                .filter(validationUtil::isValid)
                .map(d-> modelMapper.map(d, Category.class))
                .forEach(categotyRepository::save);

    }
}
