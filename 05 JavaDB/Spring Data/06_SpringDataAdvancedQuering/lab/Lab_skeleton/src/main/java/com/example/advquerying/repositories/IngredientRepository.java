package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByNameStartingWith(String name);
    List<Ingredient> findAllByNameIn(Collection<String> name);

    @Query("DELETE  FROm Ingredient i Where i.name = :name")
    @Modifying
    void deleteAllByNames(String name);
    @Query("UPDATE Ingredient i SET i.price=i.price*1.1")
    @Modifying
    int updateAllByPrice();


    @Query("UPDATE Ingredient i SET i.price=i.price* :priceChange WHERE i.name IN :names")
    @Modifying
    int updateAllByPrice(BigDecimal priceChange, Collection<String> names);
}
