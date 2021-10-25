package com.example.pathfinderdemo.service.impl;

import com.example.pathfinderdemo.Repository.RouteRepository;
import com.example.pathfinderdemo.model.view.RouteViewModel;
import com.example.pathfinderdemo.service.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;

    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RouteViewModel> getAllRoutesView() {
        return this.routeRepository.findAll()
                .stream()
                .map(route -> {
                    RouteViewModel routeViewModel=modelMapper.map(route, RouteViewModel.class);

                            if(route.getPictures().isEmpty()){
                                routeViewModel.setPictureUrl("/images/pic4.jpg");
                            } else{
                                routeViewModel.setPictureUrl(
                                        route.getPictures()
                                                .stream().findFirst().get().getUrl());
                            }
                    return routeViewModel;
                })
                .collect(Collectors.toList());
    }
}
