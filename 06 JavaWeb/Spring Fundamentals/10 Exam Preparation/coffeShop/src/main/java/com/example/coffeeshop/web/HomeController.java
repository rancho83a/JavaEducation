package com.example.coffeeshop.web;

import com.example.coffeeshop.model.view.OrderViewModel;
import com.example.coffeeshop.security.CurrentUser;
import com.example.coffeeshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final OrderService orderService;

    public HomeController(CurrentUser currentUser, OrderService orderService) {
        this.currentUser = currentUser;
        this.orderService = orderService;
    }


    @GetMapping("/")
    public String index( Model model) {
        if(currentUser.getId()==null) {
            return "index";
        }

        List<OrderViewModel> orders =orderService.findAllOrdersByPriceDesc();

        model.addAttribute("orders", orders);
        model.addAttribute("totalTime", orders.stream()
                .map(o->o.getCategory().getNeededTime())
                .reduce(Integer::sum)
                .orElse(0));

        return "home";
    }
}


