package com.example.advquerying.repositories;

import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface ShampooRepository extends BaseRepository<Shampoo> {

    @Query("SELECT s FROM Shampoo s WHERE s.brand= :brand AND s.size = :size OR s.id = :id")
    List<Shampoo> findAllByBrandAndSizeOrId(

            @Param("brand") String brand,
            @Param("size") Size size,
            @Param("id") Long id
    );

    List<Shampoo> findAllBySizeOrderById(Size size);
    List<Shampoo> findAllBySizeOrLabelIdOrderByPrice(Size size, Long labelId);

    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);
    int countAllByPriceLessThan(BigDecimal price);

    @Query("SELECT s FROM Shampoo s JOIN s.ingredients i WHERE i.name IN :names")
    List<Shampoo> findAllByIngredientsNames(Collection<String> names);

//    @Query("Select s FROM Shampoo s join s.ingredients i GROUP BY  s.id HAVING Count(s.id) < :ingredientCount")
//    List<Shampoo> findAllByIngredientsCount(Long ingredientCount);

    @Query("Select s FROM Shampoo s WHERE s.ingredients.size < :ingredientCount")
    List<Shampoo> findAllByIngredientsCount(int ingredientCount);



}
