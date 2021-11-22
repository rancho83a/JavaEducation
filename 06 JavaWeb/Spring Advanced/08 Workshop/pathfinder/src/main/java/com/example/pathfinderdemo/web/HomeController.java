package com.example.pathfinderdemo.web;

import com.example.pathfinderdemo.service.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// za index.html
@Controller
public class HomeController {
    private final PictureService pictureService;

    public HomeController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    //na kou route da slysha:
    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("pictures",
                pictureService.findAllPicturesUrl()
        );
        return "index";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
