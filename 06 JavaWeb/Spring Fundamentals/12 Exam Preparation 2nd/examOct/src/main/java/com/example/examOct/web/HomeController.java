package com.example.examOct.web;

import com.example.examOct.security.CurrentUser;
import com.example.examOct.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final UserService userService;

    public HomeController(CurrentUser currentUser, UserService userService) {
        this.currentUser = currentUser;
        this.userService = userService;
    }


    @GetMapping("/")
    public String index( Model model) {
        if(currentUser.getId()==null) {
            return "index";
        }




        return "home";
    }
}


