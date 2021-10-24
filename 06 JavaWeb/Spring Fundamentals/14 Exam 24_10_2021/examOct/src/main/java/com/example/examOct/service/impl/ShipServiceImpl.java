package com.example.examOct.service.impl;

import com.example.examOct.model.entity.ShipEntity;
import com.example.examOct.model.service.ShipServiceModel;
import com.example.examOct.model.view.AttackerViewModel;
import com.example.examOct.model.view.ShipViewModel;
import com.example.examOct.repository.ShipRepository;
import com.example.examOct.security.CurrentUser;
import com.example.examOct.service.CategoryService;
import com.example.examOct.service.ShipService;
import com.example.examOct.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipServiceImpl implements ShipService {
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final ShipRepository shipRepository;
    private final UserService userService;
    private final CurrentUser currentUser;

    public ShipServiceImpl(ModelMapper modelMapper, CategoryService categoryService, ShipRepository shipRepository, UserService userService, CurrentUser currentUser) {
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.shipRepository = shipRepository;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @Override
    public void addShip(ShipServiceModel shipServiceModel) {
        ShipEntity ship = modelMapper.map(shipServiceModel, ShipEntity.class);

        ship.setUser(userService.findById(currentUser.getId()));
        ship.setCategory(categoryService.findCategoryByCategoryNameEnum(shipServiceModel.getCategory()));

        this.shipRepository.save(ship);
    }

    @Override
    public List<ShipViewModel> findAllShips() {

        return this.shipRepository.findAll()
                .stream()
                .map(ship -> modelMapper.map(ship, ShipViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AttackerViewModel> findShipsByOwner(Long id) {
        return this.shipRepository.findByUserId(id)
                .stream()
                .map(ship -> modelMapper.map(ship, AttackerViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AttackerViewModel> findShipOfAnotherOwners(Long id) {
        return this.shipRepository.findByUserIdNot(id)
                .stream()
                .map(ship -> modelMapper.map(ship, AttackerViewModel.class))
                .collect(Collectors.toList());
            }

    @Override
    public void fire(Long id1) {
        ShipEntity attacker = this.shipRepository.findById(id1).orElse(null);
        System.out.println();

    }
}
