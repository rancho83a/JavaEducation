package bg.softuni.errors.web;

import bg.softuni.errors.exception.ObjectNotFoundException;
import bg.softuni.errors.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
    @GetMapping("/products/{id}/details")
    public String details(
            @PathVariable("id") Long productId
    ) {

        //this.productRepository.findById(id).orElseThrow(new ProductNotFoundException);
        throw new ProductNotFoundException(productId);
    }

    @GetMapping("/products/{id}/error")
    public String boom(
            @PathVariable("id") Long id
    ) {

        //this.productRepository.findById(id).orElseThrow(new ProductNotFoundException);
        throw new NullPointerException();
    }

    // Work only in this Controller
    @ExceptionHandler({ProductNotFoundException.class})
    public ModelAndView handleDBException(ProductNotFoundException e) {

        ModelAndView modelAndView = new ModelAndView("product-not-found");
        modelAndView.addObject("productId", e.getProductId());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
