package easterRaces.core;


import easterRaces.core.interfaces.Controller;
import easterRaces.entities.cars.Car;
import easterRaces.entities.cars.MuscleCar;
import easterRaces.entities.cars.SportsCar;
import easterRaces.entities.drivers.Driver;
import easterRaces.entities.drivers.DriverImpl;
import easterRaces.entities.racers.Race;
import easterRaces.entities.racers.RaceImpl;
import easterRaces.repositories.CarRepository;
import easterRaces.repositories.DriverRepository;
import easterRaces.repositories.RaceRepository;
import easterRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static easterRaces.common.ExceptionMessages.*;
import static easterRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Car> motorcycleRepository;
    private Repository<Race> raceRepository;
    private Repository<Driver> riderRepository;

    public ControllerImpl(Repository<Driver> riderRepository, Repository<Car> motorcycleRepository, Repository<Race> raceRepository) {
        this.motorcycleRepository = motorcycleRepository;
        this.raceRepository = raceRepository;
        this.riderRepository = riderRepository;
    }

    @Override
    public String createDriver(String name) {
        if (this.riderRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, name));
        }
        this.riderRepository.add(new DriverImpl(name));
        return String.format(DRIVER_CREATED, name);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car car = this.motorcycleRepository.getByName(model);
        if (car != null) {
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }
        if (type.equals("Muscle")) {
            car = new MuscleCar(model, horsePower);
        } else {
            car = new SportsCar(model, horsePower);
        }
        this.motorcycleRepository.add(car);
        return String.format(CAR_CREATED, car.getClass().getSimpleName(), model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = this.riderRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        Car car = this.motorcycleRepository.getByName(carModel);
        if (car == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }
        driver.addCar(car);
        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = this.raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        Driver driver = this.riderRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        race.addDriver(driver);
        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = this.raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        Collection<Driver> drivers = race.getDrivers();
        if (drivers.size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }

        List<Driver> sorted = drivers.stream()
                .sorted((d1, d2) -> Double.compare(
                        d2.getCar().calculateRacePoints(race.getLaps()),
                        d1.getCar().calculateRacePoints(race.getLaps())
                ))
                .limit(3)
                .collect(Collectors.toList());

        this.raceRepository.remove(race);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(DRIVER_FIRST_POSITION, sorted.get(0).getName(), raceName) + System.lineSeparator())
                .append(String.format(DRIVER_SECOND_POSITION, sorted.get(1).getName(), raceName) + System.lineSeparator())
                .append(String.format(DRIVER_THIRD_POSITION, sorted.get(2).getName(), raceName));
                return sb.toString();


    }

    @Override
    public String createRace(String name, int laps) {
        Race race = this.raceRepository.getByName(name);
        if (race != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }
        this.raceRepository.add(new RaceImpl(name, laps));

        return String.format(RACE_CREATED, name);
    }
}
