package jsoprocessing.ex.repository;

import jsoprocessing.ex.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Set<Customer> findAllByOrderByBirthDateDescYoungDriver();

    @Query("SELECT c FROM Customer c where c.sales.size>0")
    List<Customer> findAllCustomersWithCar();

}
