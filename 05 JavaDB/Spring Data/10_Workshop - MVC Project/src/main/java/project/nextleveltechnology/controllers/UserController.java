package project.nextleveltechnology.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.nextleveltechnology.model.dto.UserLoginDto;
import project.nextleveltechnology.model.dto.UserRegisterDto;
import project.nextleveltechnology.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public String register() {
        return "user/register";
    }

    @PostMapping("users/register")
    public String rerister(UserRegisterDto userRegisterDto, Model model) {
        if (this.userService.register(userRegisterDto)) {
            return "redirect:/users/login";
        }
        model.addAttribute("error", "This is error");
        return "user/register";
    }

    @GetMapping("/users/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/users/login")
    public String login(UserLoginDto user, Model model, HttpServletRequest request) {
        Long userId = this.userService.validateUserLoginAndDetails(user);

        if (userId == null) {
            model.addAttribute("error", "This is error");
            return "user/login";
        }
        request.getSession().setAttribute("userId", userId);

        return "redirect:/";
    }
}
