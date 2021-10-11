package project.nextleveltechnology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.nextleveltechnology.model.Entity.Employee;
import project.nextleveltechnology.model.Entity.Project;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsAllBy();

    Employee findFirstByFirstNameAndLastNameAndAge(String firstName, String lastName, Integer age);

    List<Employee> findAllByAgeGreaterThan(int age);
}
