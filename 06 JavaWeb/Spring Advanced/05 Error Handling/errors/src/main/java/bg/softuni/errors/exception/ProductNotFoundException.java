package bg.softuni.errors.exception;


//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason ="Object not found!")
public class ProductNotFoundException extends RuntimeException{
    private final Long productId;

    public ProductNotFoundException(Long productId) {
        super("Can not find object with id "+ productId);
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
