package com.example.examOct.web;

import com.example.examOct.model.view.AttackerViewModel;
import com.example.examOct.model.view.ShipViewModel;
import com.example.examOct.security.CurrentUser;
import com.example.examOct.service.ShipService;
import com.example.examOct.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final UserService userService;
    private final ShipService shipService;

    public HomeController(CurrentUser currentUser, UserService userService, ShipService shipService) {
        this.currentUser = currentUser;
        this.userService = userService;
        this.shipService = shipService;
    }


    @GetMapping("/")
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }

        List<ShipViewModel> ships = this.shipService.findAllShips();

       List<AttackerViewModel> attackers=  this.shipService.findShipsByOwner(currentUser.getId());
       List<AttackerViewModel> defenders=  this.shipService.findShipOfAnotherOwners(currentUser.getId());


        model.addAttribute("ships", ships);
        model.addAttribute("attackers", attackers);
        model.addAttribute("defenders", defenders);



        return "home";
    }
}


