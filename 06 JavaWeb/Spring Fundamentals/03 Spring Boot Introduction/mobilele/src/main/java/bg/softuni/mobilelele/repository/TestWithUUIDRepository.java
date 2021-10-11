package bg.softuni.mobilelele.repository;

import bg.softuni.mobilelele.model.entity.TestEntityWithUUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TestWithUUIDRepository extends JpaRepository<TestEntityWithUUID, UUID> {

}
