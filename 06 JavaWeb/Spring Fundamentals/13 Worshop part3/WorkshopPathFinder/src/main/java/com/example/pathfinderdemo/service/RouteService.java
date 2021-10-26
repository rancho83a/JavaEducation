package com.example.pathfinderdemo.service;

import com.example.pathfinderdemo.model.service.RouteServiceModel;
import com.example.pathfinderdemo.model.view.RouteDetailsViewModel;
import com.example.pathfinderdemo.model.view.RouteViewModel;

import java.util.List;

public interface RouteService {
    List<RouteViewModel> getAllRoutesView();

    void addNewRoute(RouteServiceModel routeServiceModel);

    RouteDetailsViewModel findRouteById(Long id);
}
