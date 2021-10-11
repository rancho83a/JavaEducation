package jsoprocessing.ex.repository;

import jsoprocessing.ex.model.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sale,Long> {
}
