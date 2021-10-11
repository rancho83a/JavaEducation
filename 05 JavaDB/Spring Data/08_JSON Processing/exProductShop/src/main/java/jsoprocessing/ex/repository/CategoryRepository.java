package jsoprocessing.ex.repository;

import jsoprocessing.ex.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository <Category, Long>{

    @Query("Select c FROM Category c ORDER BY c.products.size DESC" )
    List<Category> findAllOrderByProductsCount();

}
