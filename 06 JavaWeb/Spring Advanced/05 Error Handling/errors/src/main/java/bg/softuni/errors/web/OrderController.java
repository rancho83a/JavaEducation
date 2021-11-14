package bg.softuni.errors.web;

import bg.softuni.errors.exception.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {
    @GetMapping("/orders/{id}/details")
    public String details(
            @PathVariable("id") Long orderId
    ) {

        //this.productRepository.findById(id).orElseThrow(new ProductNotFoundException);
        throw new ObjectNotFoundException(orderId);
    }

}
