package com.example.examOct.web;

import com.example.examOct.model.entity.CategoryNameEnum;
import com.example.examOct.model.view.ProductViewModel;
import com.example.examOct.security.CurrentUser;
import com.example.examOct.service.ProductService;
import com.example.examOct.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.stream.Collectors;


@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final UserService userService;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public HomeController(CurrentUser currentUser, UserService userService, ProductService productService, ModelMapper modelMapper) {
        this.currentUser = currentUser;
        this.userService = userService;

        this.productService = productService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/")
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }

        BigDecimal totalSum = this.productService.getTotalProductsSum();
        if (null == (totalSum)) {
            totalSum = BigDecimal.ZERO;
        }

        model.addAttribute("totalSum", totalSum);

        model.addAttribute("drinkProducts",
                this.productService.getProductsByCategory(CategoryNameEnum.DRINK).stream()
                        .map(p -> modelMapper.map(p, ProductViewModel.class))
                        .collect(Collectors.toList()));

        model.addAttribute("foodProducts",
                this.productService.getProductsByCategory(CategoryNameEnum.FOOD).stream()
                        .map(p -> modelMapper.map(p, ProductViewModel.class))
                        .collect(Collectors.toList()));

        model.addAttribute("houseHoldProducts",
                this.productService.getProductsByCategory(CategoryNameEnum.HOUSEHOLD).stream()
                        .map(p -> modelMapper.map(p, ProductViewModel.class))
                        .collect(Collectors.toList()));

        model.addAttribute("otherProducts",
                this.productService.getProductsByCategory(CategoryNameEnum.OTHER).stream()
                        .map(p -> modelMapper.map(p, ProductViewModel.class))
                        .collect(Collectors.toList()));
        return "home";
    }


}


