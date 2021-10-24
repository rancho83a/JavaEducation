package com.example.examOct.service;

import com.example.examOct.model.service.ShipServiceModel;
import com.example.examOct.model.view.AttackerViewModel;
import com.example.examOct.model.view.ShipViewModel;

import java.util.List;

public interface ShipService {
    void addShip(ShipServiceModel shipServiceModel);
    List<ShipViewModel> findAllShips();

    List<AttackerViewModel> findShipsByOwner(Long id);

    List<AttackerViewModel> findShipOfAnotherOwners(Long id);

    void fire(Long id1);
}
