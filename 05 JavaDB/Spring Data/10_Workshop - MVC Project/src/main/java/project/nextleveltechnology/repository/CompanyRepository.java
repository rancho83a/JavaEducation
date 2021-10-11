package project.nextleveltechnology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.nextleveltechnology.model.Entity.Company;
import project.nextleveltechnology.model.Entity.Project;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsAllBy();

    Company findFirstByName(String name);
}
