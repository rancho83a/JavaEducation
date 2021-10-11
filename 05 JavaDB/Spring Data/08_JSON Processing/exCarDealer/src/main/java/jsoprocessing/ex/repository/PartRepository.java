package jsoprocessing.ex.repository;

import jsoprocessing.ex.model.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part,Long> {
}
