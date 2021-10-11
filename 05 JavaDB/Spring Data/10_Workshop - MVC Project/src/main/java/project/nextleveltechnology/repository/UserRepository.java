package project.nextleveltechnology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.nextleveltechnology.model.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsernameOrEmail(String username, String email);

    User findFirstByUsername(String username);
}
