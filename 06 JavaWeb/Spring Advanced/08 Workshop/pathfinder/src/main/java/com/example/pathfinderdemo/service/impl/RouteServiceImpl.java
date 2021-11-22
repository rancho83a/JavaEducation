package com.example.pathfinderdemo.service.impl;

import com.example.pathfinderdemo.Repository.RouteRepository;
import com.example.pathfinderdemo.model.entity.RouteEntity;
import com.example.pathfinderdemo.model.service.RouteServiceModel;
import com.example.pathfinderdemo.model.view.RouteDetailsViewModel;
import com.example.pathfinderdemo.model.view.RouteViewModel;
import com.example.pathfinderdemo.service.CategoryService;
import com.example.pathfinderdemo.service.RouteService;
import com.example.pathfinderdemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;

    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper modelMapper, UserService userService, CategoryService categoryService) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public List<RouteViewModel> getAllRoutesView() {
        return this.routeRepository.findAll()
                .stream()
                .map(route -> {
                    RouteViewModel routeViewModel = modelMapper.map(route, RouteViewModel.class);

                    if (route.getPictures().isEmpty()) {
                        routeViewModel.setPictureUrl("/images/pic4.jpg");
                    } else {
                        routeViewModel.setPictureUrl(
                                route.getPictures()
                                        .stream().findFirst().get().getUrl());
                    }
                    return routeViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addNewRoute(RouteServiceModel routeServiceModel) {

        RouteEntity newRoute = modelMapper.map(routeServiceModel, RouteEntity.class);
//TODO set author
        //newRoute.setAuthor(userService.findCurrentLoginUserEntity());
        newRoute.setCategories(
                routeServiceModel.getCategories()
                        .stream()
                        // .map(categoryEnum -> this.categoryService.findCategoryByCategoryName(categoryEnum))
                        .map(this.categoryService::findCategoryByCategoryName)
                        .collect(Collectors.toSet())
        );
        this.routeRepository.save(newRoute);

    }

    @Override
    public RouteDetailsViewModel findRouteById(Long id) {
        RouteEntity route = this.routeRepository.findById(id).orElse(null);
        RouteDetailsViewModel routeDetailsView = modelMapper.map(route, RouteDetailsViewModel.class);

        routeDetailsView.setAuthor(route.getAuthor().getFullName());
        return routeDetailsView;
    }
}
