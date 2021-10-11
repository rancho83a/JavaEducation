package jsoprocessing.ex.service;

import jsoprocessing.ex.model.Dto.out.CarIdMAkeModelDistanceDto;
import jsoprocessing.ex.model.Dto.out.task4.CarExportDto;
import jsoprocessing.ex.model.entity.Car;

import java.io.IOException;
import java.util.List;

public interface CarService {
    void seedCars() throws IOException;

    Car getRandomCar();

    List<CarIdMAkeModelDistanceDto> getAllCarsMakeBy(String make);
    List<CarExportDto>getAllCars();
}
