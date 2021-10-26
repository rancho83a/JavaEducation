package com.example.pathfinderdemo.web;

import com.example.pathfinderdemo.model.binding.RouteAddBindingModel;
import com.example.pathfinderdemo.model.service.RouteServiceModel;
import com.example.pathfinderdemo.service.RouteService;
import com.example.pathfinderdemo.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/routes")
public class RouteController {
    private final RouteService routeService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public RouteController(RouteService routeService, CurrentUser currentUser, ModelMapper modelMapper) {
        this.routeService = routeService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String allRoutes(Model model) {
        model.addAttribute("routes", this.routeService.getAllRoutesView());
        return "routes";
    }

    @GetMapping("/add")
    public String add() {
        if(currentUser.getId()==null){
            return "redirect:/users/login";
        }
        return "add-route";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model){
        model.addAttribute("route",this.routeService.findRouteById(id));

        return "route-details";
    }



    @PostMapping("/add")
    public String addRoute(@Valid RouteAddBindingModel routeAddBindingModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        if(bindingResult.hasErrors()){
           redirectAttributes.addFlashAttribute("routeAddBindingModel", routeAddBindingModel);
           redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.routeAddBindingModel",bindingResult);
       return "redirect:add";
        }

        RouteServiceModel routeServiceModel = modelMapper.map(routeAddBindingModel, RouteServiceModel.class);

        routeServiceModel.setGpxCoordinate(new String(routeAddBindingModel.getGpxCoordinates().getBytes()));

        routeService.addNewRoute(routeServiceModel);
        return "redirect:all";
    }

    @ModelAttribute
    public RouteAddBindingModel routeAddBindingModel() {
        return new RouteAddBindingModel();
    }
}

