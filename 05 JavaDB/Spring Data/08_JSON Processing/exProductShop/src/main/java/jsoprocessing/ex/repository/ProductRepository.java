package jsoprocessing.ex.repository;

import jsoprocessing.ex.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal low, BigDecimal up);

}
