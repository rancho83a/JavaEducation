package jsoprocessing.ex.repository;

import jsoprocessing.ex.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategotyRepository extends JpaRepository <Category, Long>{
}
