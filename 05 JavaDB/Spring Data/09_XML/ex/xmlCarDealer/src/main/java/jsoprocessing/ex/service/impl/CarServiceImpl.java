package jsoprocessing.ex.service.impl;

import jsoprocessing.ex.model.Dto.CarSeedDto;
import jsoprocessing.ex.model.Dto.query2.CarInfoDto;
import jsoprocessing.ex.model.Dto.query2.CarRootDto;
import jsoprocessing.ex.model.Dto.query4.CarInfoWithPartsDto;
import jsoprocessing.ex.model.Dto.query4.CarWitPartsRootDto;
import jsoprocessing.ex.model.Dto.query4.PartNamePriceDto;
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
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ValidationUtil validationUtil;
    private final SupplierService supplierService;
    private final PartService partService;
    private final ModelMapper modelMapper;


    public CarServiceImpl(CarRepository carRepository, ValidationUtil validationUtil, SupplierService supplierService, PartService partService, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.validationUtil = validationUtil;
        this.supplierService = supplierService;
        this.partService = partService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long getCarsCount() {
        return this.carRepository.count();
    }

    @Override
    @Transactional
    public void seedCars(List<CarSeedDto> cars) {
        cars.stream()
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
        Long randomId = ThreadLocalRandom.current().nextLong(1, this.carRepository.count() + 1);
        return this.carRepository.findById(randomId).orElse(null);
    }

    @Override
    public CarRootDto getAllCarsMakeBy(String make) {
        CarRootDto carRootDto = new CarRootDto();
        carRootDto.setCars(
                this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make)
                        .stream()
                        .map(car -> modelMapper.map(car, CarInfoDto.class))
                        .collect(Collectors.toList())
        );


        return carRootDto;
    }

    @Override
    public CarWitPartsRootDto getAllCars() {

        CarWitPartsRootDto carWitPartsRootDto = new CarWitPartsRootDto();

     carWitPartsRootDto.setCars(
                this.carRepository.findAllBy().stream()
                        .map(car -> {
                            CarInfoWithPartsDto carInfo = modelMapper.map(car, CarInfoWithPartsDto.class);

                            carInfo.setParts(
                                    car.getParts().stream()
                                            .map(part ->
                                                    modelMapper.map(part, PartNamePriceDto.class)
                                            ).collect(Collectors.toList())
                            );
                            return carInfo;
                        })
                        .collect(Collectors.toList())
     );
        return carWitPartsRootDto;
    }


}
