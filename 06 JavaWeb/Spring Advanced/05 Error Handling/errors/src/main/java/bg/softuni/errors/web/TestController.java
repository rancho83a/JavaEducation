package bg.softuni.errors.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TestController {
    @GetMapping("/test")
    public String testError(){
        if(true){
            throw  new NullPointerException("It`s my error");
        }

        return "hello";
    }

}
