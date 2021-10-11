package ex.xmlprocessing.service.impl;

import ex.xmlprocessing.Dto.CategorySeedDto;
import ex.xmlprocessing.Dto.ProductSeedDto;
import ex.xmlprocessing.Dto.query1.ProductNamePriceSellerDto;
import ex.xmlprocessing.Dto.query1.ProductViewRootDto;
import ex.xmlprocessing.Repository.ProductRepository;
import ex.xmlprocessing.model.entity.Product;
import ex.xmlprocessing.service.CategoryService;
import ex.xmlprocessing.service.ProductService;
import ex.xmlprocessing.service.UserService;
import ex.xmlprocessing.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private  final ValidationUtil validationUtil;
    private final UserService userService;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public long getProductCount() {
        return this.productRepository.count();
    }

    @Override
    public void seedProducts(List<ProductSeedDto> products) {
        products.stream()
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
    public ProductViewRootDto getAllProductInRange(BigDecimal low, BigDecimal up) {
        List<Product> allProducts = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(low, up);

        List<ProductNamePriceSellerDto> list = allProducts.stream()
                .map(product -> {
                    ProductNamePriceSellerDto productNamePriceSellerDto = modelMapper.map(product, ProductNamePriceSellerDto.class);

                    String firstName="";
                    if(product.getSeller().getFirstName()!=null){
                        firstName=product.getSeller().getFirstName();
                    }
                    productNamePriceSellerDto.setSeller(firstName+ " " + product.getSeller().getLastName());
                    return productNamePriceSellerDto;
                })
                .collect(Collectors.toList());
        ProductViewRootDto productViewRootDto = new ProductViewRootDto();
        productViewRootDto.setProducts(list);

return productViewRootDto;

    }
}
