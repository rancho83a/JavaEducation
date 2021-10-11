package ex.xmlprocessing.Repository;

import ex.xmlprocessing.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("Select c FROM Category c ORDER BY c.products.size DESC" )
    List<Category> findAllOrderByProductsCount();

}
