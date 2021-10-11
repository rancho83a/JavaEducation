package com.example.advquerying.repositories;


import com.example.advquerying.entities.Ingredient;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class IngredientDaoImpl implements IngredientDao {

private EntityManager entityManager;

    public IngredientDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Ingredient> createQueryAllIngredient(){
        return entityManager.createQuery("SELECT i FROM Ingredient i", Ingredient.class).getResultList();
    }
}
