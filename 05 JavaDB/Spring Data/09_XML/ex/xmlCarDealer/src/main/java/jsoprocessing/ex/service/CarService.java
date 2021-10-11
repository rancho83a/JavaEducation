package jsoprocessing.ex.service;

import jsoprocessing.ex.model.Dto.CarSeedDto;
import jsoprocessing.ex.model.Dto.query2.CarRootDto;
import jsoprocessing.ex.model.Dto.query4.CarInfoWithPartsDto;
import jsoprocessing.ex.model.Dto.query4.CarWitPartsRootDto;
import jsoprocessing.ex.model.entity.Car;

import java.util.List;

public interface CarService {
    Long getCarsCount();

    void seedCars(List<CarSeedDto> cars);

    Car getRandomCar();

    CarRootDto getAllCarsMakeBy(String make);

    CarWitPartsRootDto getAllCars();
}
