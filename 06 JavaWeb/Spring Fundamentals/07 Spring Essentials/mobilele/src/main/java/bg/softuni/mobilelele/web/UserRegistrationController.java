package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.binding.UserRegistrationBindingModel;
import bg.softuni.mobilelele.model.service.UserRegistrationServiceModel;
import bg.softuni.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegistrationController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRegistrationController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/users/register")
    public String registerUser() {
        return "auth-register";
    }

//    //Variant za ModelView
//    @GetMapping("/users/register")
//    public String registerUser(Model model) {
//        model.addAttribute("name", "value");
//        return "auth-register";
//    }

    //Variant za ModelView
//    @GetMapping("/users/register")
//    public ModelAndView registerUser(ModelAndView modelAndView){
//        modelAndView.setViewName("auth-register");
//        return modelAndView;
//    }

    @PostMapping("/users/register")
    public String register(UserRegistrationBindingModel userRegistrationBindingModel, Model model) {

        UserRegistrationServiceModel userRegistrationServiceModel = modelMapper.map(userRegistrationBindingModel, UserRegistrationServiceModel.class);
        // TODO - add to flash attributes


        if (!userService.isUserNameFree(userRegistrationServiceModel.getUsername())) {
            model.addAttribute("userNameOccupied", true);
            return "redirect:/users/register";
        } else {
            userService.registerAndLoginUser(userRegistrationServiceModel);
        }
        return "redirect:/";
    }
}
