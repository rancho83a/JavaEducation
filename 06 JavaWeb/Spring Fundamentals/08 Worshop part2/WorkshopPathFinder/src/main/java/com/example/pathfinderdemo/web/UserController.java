package com.example.pathfinderdemo.web;

import com.example.pathfinderdemo.model.binding.UserRegisterBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }

    @GetMapping("/register")
    public String register(Model model) {
        //kogato za 1st time dostapvame /register, oshte nqm ainfo za userRegisterBindingModel, zatova moje da proverim
        // ili s @ModelAttribute:
//        if(!model.containsAttribute("userRegisterBindingModel")){
//            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
//        }

        return "register";
    }

    @PostMapping("/register")
        public String registerConfirm(
            @Valid UserRegisterBindingModel userRegisterBindingModel,
            BindingResult bindingResult, RedirectAttributes redirectAttributes){

        //ako ima greshki pri populvanet na front-end -> redirect kam /users/register, no da sa zapazeni
        // starite stounosti na poleta - stava s RedirectAttributes redirectAttributes
        if(bindingResult.hasErrors() ||
                !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                    bindingResult);

            return "redirect:register";
        }
        //TODO
        return "redirect:/";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
