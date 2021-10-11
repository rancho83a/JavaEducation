package jsoprocessing.ex;

import com.google.gson.Gson;
import jsoprocessing.ex.model.Dto.viewDto.CategoryStatisticDto;
import jsoprocessing.ex.model.Dto.viewDto.ProductNamePriceSellerDto;
import jsoprocessing.ex.model.Dto.viewDto.UserSoldProductsDto;
import jsoprocessing.ex.model.Dto.viewDto.task4.UsersOutputDto;
import jsoprocessing.ex.service.CategoryService;
import jsoprocessing.ex.service.ProductService;
import jsoprocessing.ex.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private static final String OUTPUT_PATH = "src/main/resources/out/";
    private static final String PRODUCTS_IN_RANGE = "products-in-range.json";
    private static final String USERS_AND_SOLD_PRODUCTS = "users-sold-products.json";
    private static final String CATEGORIES_BY_PRODUCT_COUNT= "categories-product-count.json";
    private static final String USERS_AND_PRODUCTS_TASK4= "user-products -task4.json";

    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final BufferedReader reader;
    private final Gson gson;


    public CommandLineRunnerImpl(CategoryService categoryService, UserService userService, ProductService productService, Gson gson) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.gson = gson;
        this.reader = new BufferedReader(new InputStreamReader(System.in));

    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
        System.out.println("Enter task number:");

        switch (reader.readLine()) {
            case "1" -> productInRange();
            case "2" -> successfullySoldProducts();
            case "3" -> categoriesByProductsCount();
            case"4" -> usersAndProducts();
            default -> System.out.println("Enter correct Task number!!!");
        }

    }

    private void usersAndProducts() throws IOException {
        UsersOutputDto outputDto = this.userService.getAllBySoldProductsWithOneBuyerOrderSoldProductCount();
        writeToFile(OUTPUT_PATH+USERS_AND_PRODUCTS_TASK4, gson.toJson(outputDto));

    }

    private void categoriesByProductsCount() throws IOException {

        List<CategoryStatisticDto> categoryStatisticDtos = this.categoryService.getAllCategoriesWithProducts();
        writeToFile(OUTPUT_PATH+CATEGORIES_BY_PRODUCT_COUNT, gson.toJson(categoryStatisticDtos));
    }

    private void successfullySoldProducts() throws IOException {
        List<UserSoldProductsDto> userSoldProductsDtos = this.userService.getAllBySoldProductsWithOneBuyer();
        writeToFile(OUTPUT_PATH+USERS_AND_SOLD_PRODUCTS,gson.toJson(userSoldProductsDtos));
    }

    private void productInRange() throws IOException {
        List<ProductNamePriceSellerDto> productDtos = productService.getAllProductsInRangeWithoutByuerOrderByPrice(
                BigDecimal.valueOf(500), BigDecimal.valueOf(1000)
        );

        String out = gson.toJson(productDtos);
        writeToFile(OUTPUT_PATH + PRODUCTS_IN_RANGE, out);
    }

    private void writeToFile(String filePath, String content) throws IOException {
        Files.write(Path.of(filePath), Collections.singleton(content));

    }

    private void seedData() throws IOException {
        this.categoryService.seedCategories();
        this.userService.seedUsers();
        this.productService.seedProducts();
    }
}
