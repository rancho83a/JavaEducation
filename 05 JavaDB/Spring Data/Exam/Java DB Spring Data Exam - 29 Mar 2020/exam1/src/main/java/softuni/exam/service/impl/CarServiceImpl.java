package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Dto.CarSeedDto;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private static final String CAR_FILE_PATH = "src/main/resources/files/json/cars.json";

    private final CarRepository carRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public CarServiceImpl(CarRepository carRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.carRepository = carRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return Files.readString(Path.of(CAR_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException {
        StringBuilder sb = new StringBuilder();

        CarSeedDto[] carSeedDtos = gson.fromJson(readCarsFileContent(), CarSeedDto[].class);
        Arrays.stream(carSeedDtos)
                .filter(c -> {

                    boolean isValid = validationUtil.isValid(c);

                    sb.append(
                            isValid ? String.format("Successfully imported car - %s - %s", c.getMake(), c.getModel())
                                    : "Invalid car"
                    ).append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> modelMapper.map(dto, Car.class))
                .forEach(carRepository::save);

        return sb.toString();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        StringBuilder sb = new StringBuilder();

        this.carRepository.findAllByOrderedByPicturesCountThenByMake()
                .forEach(car ->
                        sb.append(
                                String.format("Car make - %s, model - %s\n" +
                                                "\tKilometers - %d\n" +
                                                "\tRegistered on - %s\n" +
                                                "\tNumber of pictures - %d",
                                        car.getMake(), car.getModel(), car.getKilometers(), car.getRegisteredOn(), car.getPictures().size()))
                        .append(System.lineSeparator())

                );
        return sb.toString();
    }

    @Override
    public Car getCarById(Long id) {
        return this.carRepository.findById(id).orElse(null);
    }
}
