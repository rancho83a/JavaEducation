package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;

import java.util.List;

public interface IngredientDao {
    List<Ingredient> createQueryAllIngredient();
}
