package project.nextleveltechnology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.nextleveltechnology.model.Entity.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    boolean existsAllBy();

    Project findFirstByNameAndCompanyName(String name, String company_name);

    List<Project> findAllByFinishedIsTrue();
}
