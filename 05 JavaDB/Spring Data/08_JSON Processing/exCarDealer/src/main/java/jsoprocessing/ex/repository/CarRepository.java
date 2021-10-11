package jsoprocessing.ex.repository;

import jsoprocessing.ex.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);

    List<Car> findAllBy();
}
