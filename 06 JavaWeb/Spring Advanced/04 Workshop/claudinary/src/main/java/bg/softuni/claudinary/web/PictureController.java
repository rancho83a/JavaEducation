package bg.softuni.claudinary.web;

import bg.softuni.claudinary.model.binding.PictureAddBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PictureController {

    @GetMapping("/pictures/add")
    public String addPicture(){

        return "add";
    }

    @PostMapping("/pictures/add")
    public String addPicture(PictureAddBindingModel pictureAddBindingModel){
    //todo

        return "redirect:/pictures/all";
    }

    @GetMapping("/pictures/all")
    private String all(Model mode){

        //todo
        return "all";
    }
}
