package jsoprocessing.ex.service.impl;

import com.google.gson.Gson;
import jsoprocessing.ex.constant.GlobalConstant;
import jsoprocessing.ex.model.Dto.ProductSeedDto;
import jsoprocessing.ex.model.Dto.viewDto.ProductNamePriceSellerDto;
import jsoprocessing.ex.model.entity.Product;
import jsoprocessing.ex.repository.ProductRepository;
import jsoprocessing.ex.service.CategoryService;
import jsoprocessing.ex.service.ProductService;
import jsoprocessing.ex.service.UserService;
import jsoprocessing.ex.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String PRODUCT_FILE_NAME="products.json";
    private final ProductRepository productRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedProducts() throws IOException {

        if(productRepository.count()>0){
            return;
        }
        String productJson = Files.readString(Path.of(GlobalConstant.RESOURCE_FILE_PATH + PRODUCT_FILE_NAME));

        ProductSeedDto[] productSeedDtos = gson.fromJson(productJson, ProductSeedDto[].class);
        Arrays.stream(productSeedDtos)
                .filter(validationUtil::isValid)
                .map(d-> {
                    Product product = modelMapper.map(d, Product.class);
                    product.setSeller(this.userService.getRandomUser());

                    boolean randomBuyer = ThreadLocalRandom.current().nextBoolean();
                    if(randomBuyer){
                        product.setBuyer(this.userService.getRandomUser());
                    }
                    product.setCategories(this.categoryService.getRandomCategory());
                    return  product;
                })
                .forEach(productRepository::save);
    }

    @Override
    public List<ProductNamePriceSellerDto> getAllProductsInRangeWithoutByuerOrderByPrice(BigDecimal low, BigDecimal up) {
        List<ProductNamePriceSellerDto> list = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(low, up)
                .stream()
                .map(product -> {
                    ProductNamePriceSellerDto productNamePriceSellerDto = modelMapper.map(product, ProductNamePriceSellerDto.class);

                    productNamePriceSellerDto.setSeller(product.getSeller().getFirstName()+" "+product.getSeller().getLastName());
                    return productNamePriceSellerDto;
                })
                .collect(Collectors.toList());

        return list;
    }
}
