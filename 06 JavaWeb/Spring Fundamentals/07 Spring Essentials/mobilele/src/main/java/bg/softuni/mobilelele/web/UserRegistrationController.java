package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.binding.UserRegistrationBindingModel;
import bg.softuni.mobilelele.model.service.UserRegistrationServiceModel;
import bg.softuni.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserRegistrationController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRegistrationController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userModel")
    public UserRegistrationBindingModel userModel(){
        return new UserRegistrationBindingModel();
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
    public String register(

            @Valid UserRegistrationBindingModel userModel,  // throw 404 error
            BindingResult bindingResult, // contains errors of previous validation; injectirane vednaga sled @Valid!!!
            RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
            return "redirect:/users/register";
        }

        UserRegistrationServiceModel userRegistrationServiceModel = modelMapper
                .map(userModel, UserRegistrationServiceModel.class);


        if (!userService.isUserNameFree(userRegistrationServiceModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("userNameOccupied", true);

            return "redirect:/users/register";
        } else {
            userService.registerAndLoginUser(userRegistrationServiceModel);
        }
        return "redirect:/";
    }
}
