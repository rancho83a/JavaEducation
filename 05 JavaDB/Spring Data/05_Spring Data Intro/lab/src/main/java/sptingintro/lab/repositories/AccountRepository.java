package sptingintro.lab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sptingintro.lab.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
