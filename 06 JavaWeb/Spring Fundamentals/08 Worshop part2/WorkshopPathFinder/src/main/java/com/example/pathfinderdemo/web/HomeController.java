package com.example.pathfinderdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// za index.html
@Controller
public class HomeController {

    //na kou route da slysha:
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
