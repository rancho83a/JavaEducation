package com.example.pathfinderdemo.web;

import com.example.pathfinderdemo.model.view.RouteViewModel;
import com.example.pathfinderdemo.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/all")
    public String allRoutes(Model model){
        model.addAttribute("routes",this.routeService.getAllRoutesView());
        return "routes";
    }

    @GetMapping("/add")
    public String add(){
        return "add-route";
    }
}

