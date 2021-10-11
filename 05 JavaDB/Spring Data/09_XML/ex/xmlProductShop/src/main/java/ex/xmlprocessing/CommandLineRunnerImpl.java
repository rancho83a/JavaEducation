package ex.xmlprocessing;

import ex.xmlprocessing.Dto.CategorySeedRootDto;
import ex.xmlprocessing.Dto.ProductSeedRootDto;
import ex.xmlprocessing.Dto.UserSeedRootDto;
import ex.xmlprocessing.Dto.query1.ProductNamePriceSellerDto;
import ex.xmlprocessing.Dto.query1.ProductViewRootDto;
import ex.xmlprocessing.Dto.query2.UserViewRootDto;
import ex.xmlprocessing.Dto.query3.CategoryRootDto;
import ex.xmlprocessing.Dto.query4.UserRootOutDto;
import ex.xmlprocessing.service.CategoryService;
import ex.xmlprocessing.service.ProductService;
import ex.xmlprocessing.service.UserService;
import ex.xmlprocessing.util.XMLParse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private static final String RESOURCE_PATH = "src/main/resources/files/";
    private static final String OUTPUT_PATH = "src/main/resources/files/out/";
    private static final String CATEGORY_FILE_NAME = "categories.xml";
    private static final String USER_FILE_NAME = "users.xml";
    private static final String PRODUCT_FILE_NAME = "products.xml";
    private static final String QUERY1_FILE_NAME = "query1.xml";
    private static final String QUERY2_FILE_NAME = "query2.xml";
    private static final String QUERY3_FILE_NAME = "query3.xml";
    private static final String QUERY4_FILE_NAME = "query4.xml";

    private final XMLParse xmlParse;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final BufferedReader reader;

    public CommandLineRunnerImpl(XMLParse xmlParse, CategoryService categoryService, UserService userService,
                                 ProductService productService) {
        this.xmlParse = xmlParse;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }


    @Override
    public void run(String... args) throws Exception {
        seedData();
        System.out.println("Enter task name:");
        switch (reader.readLine()) {
            case "1" -> query1();
            case "2" -> query2();
            case "3" -> query3();
            case "4" -> query4();
        }


    }

    private void query4() throws JAXBException {
        UserRootOutDto userOut = this.userService.findAllBySoldProductsWithOneBuyerOrderByCountSoldProductDescThenLastName();
        xmlParse.writeToFile(OUTPUT_PATH+QUERY4_FILE_NAME, userOut);

    }

    private void query3() throws JAXBException {
        CategoryRootDto allCategoryOrderByProductsCount = this.categoryService.getAllCategoryOrderByProductsCount();
        xmlParse.writeToFile(OUTPUT_PATH + QUERY3_FILE_NAME, allCategoryOrderByProductsCount);

    }

    private void query2() throws JAXBException {
        UserViewRootDto userRoot = this.userService.getALlUsersWith1SoldProductAtLeast();
        xmlParse.writeToFile(OUTPUT_PATH + QUERY2_FILE_NAME, userRoot);
    }

    private void query1() throws JAXBException {
        ProductViewRootDto productViewRootDto = this.productService.getAllProductInRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        xmlParse.writeToFile(OUTPUT_PATH + QUERY1_FILE_NAME, productViewRootDto);
    }

    private void seedData() throws JAXBException, FileNotFoundException {
        seedCategories();
        seedUsers();
        seedProduct();
    }

    private void seedProduct() throws JAXBException, FileNotFoundException {
        if (this.productService.getProductCount() > 0) {
            return;
        }
        ProductSeedRootDto productSeedRootDto = xmlParse.fromFile(RESOURCE_PATH + PRODUCT_FILE_NAME, ProductSeedRootDto.class);
        this.productService.seedProducts(productSeedRootDto.getProducts());
    }

    private void seedUsers() throws JAXBException, FileNotFoundException {
        if (this.userService.getUserCount() > 0) {
            return;
        }
        UserSeedRootDto userSeedRootDto = xmlParse.fromFile(RESOURCE_PATH + USER_FILE_NAME, UserSeedRootDto.class);
        this.userService.seedUsers(userSeedRootDto.getUsers());
    }

    private void seedCategories() throws JAXBException, FileNotFoundException {
        if (this.categoryService.getCategoryCount() > 0) {
            return;
        }
        CategorySeedRootDto categorySeedRootDto = xmlParse.fromFile(RESOURCE_PATH + CATEGORY_FILE_NAME, CategorySeedRootDto.class);
        this.categoryService.seedCategories(categorySeedRootDto.getCategories());
    }


}
