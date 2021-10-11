package sptingintro.lab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sptingintro.lab.models.User;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String username);
}
