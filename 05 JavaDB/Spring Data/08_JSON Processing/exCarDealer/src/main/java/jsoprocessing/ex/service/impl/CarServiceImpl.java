package jsoprocessing.ex.service.impl;

import com.google.gson.Gson;
import jsoprocessing.ex.constant.GlobalConstant;
import jsoprocessing.ex.model.Dto.CarSeedDto;
import jsoprocessing.ex.model.Dto.out.CarIdMAkeModelDistanceDto;
import jsoprocessing.ex.model.Dto.out.task4.CarExportDto;
import jsoprocessing.ex.model.Dto.out.task4.CarMakeModelDistanceDto;
import jsoprocessing.ex.model.Dto.out.task4.PartNamePriceDto;
import jsoprocessing.ex.model.entity.Car;
import jsoprocessing.ex.model.entity.Part;
import jsoprocessing.ex.repository.CarRepository;
import jsoprocessing.ex.service.CarService;
import jsoprocessing.ex.service.PartService;
import jsoprocessing.ex.service.SupplierService;
import jsoprocessing.ex.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private static final String CAR_FILE_NAME = "cars.json";
    private final CarRepository carRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final SupplierService supplierService;
    private final PartService partService;


    public CarServiceImpl(CarRepository carRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, SupplierService supplierService, PartService partService) {
        this.carRepository = carRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.supplierService = supplierService;
        this.partService = partService;
    }


    @Override
    @Transactional
    public void seedCars() throws IOException {
        if (carRepository.count() > 0) {
            return;
        }
        String carJson = Files.readString(Path.of(GlobalConstant.RESOURCE_FILE_PATH + CAR_FILE_NAME));

        CarSeedDto[] carSeedDtos = gson.fromJson(carJson, CarSeedDto[].class);
        Arrays.stream(carSeedDtos)
                .filter(validationUtil::isValid)
                .map(d -> {
                    Car car = modelMapper.map(d, Car.class);
                    Set<Part> randomPartSet = this.partService.getRandomPartSet();

                    car.setParts(randomPartSet);
                    return car;
                })
                .forEach(carRepository::save);
    }

    @Override
    public Car getRandomCar() {
        Long randomId = ThreadLocalRandom.current().nextLong(1, this.carRepository.count()+1 );
                return this.carRepository.findById(randomId).orElse(null);
    }

    @Override
    public List<CarIdMAkeModelDistanceDto> getAllCarsMakeBy(String make) {
        List<Car> cars = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make);

               return cars.stream()
                .map(car-> modelMapper.map(car, CarIdMAkeModelDistanceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarExportDto> getAllCars() {
        List<Car> cars = this.carRepository.findAllBy();
        List<CarExportDto> exportList = cars.stream()
                .map(car -> {
                    CarExportDto carExportDto = new CarExportDto();
                    CarMakeModelDistanceDto carDetails = modelMapper.map(car, CarMakeModelDistanceDto.class);

                    List<PartNamePriceDto> parts = car.getParts().stream()
                            .map(part -> modelMapper.map(part, PartNamePriceDto.class)).collect(Collectors.toList());


                    carExportDto.setCar(carDetails);
                    carExportDto.setParts(parts);


                    return carExportDto;

                }).collect(Collectors.toList());

        return exportList;
    }
}

