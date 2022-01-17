package com.example.examOct.repository;

import com.example.examOct.model.entity.CategoryNameEnum;
import com.example.examOct.model.entity.ProductEntity;
import com.example.examOct.model.service.ProductServiceModel;
import com.example.examOct.service.ProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT  SUM(p.price) FROM ProductEntity p")
    BigDecimal totalProductsSum();

    List<ProductEntity> findAllByCategory_Name(CategoryNameEnum categoryName);
}
