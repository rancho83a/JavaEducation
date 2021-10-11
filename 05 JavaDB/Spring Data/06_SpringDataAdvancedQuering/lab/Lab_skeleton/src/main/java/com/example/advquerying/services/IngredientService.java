package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface IngredientService {

    List<String>  getAllByNamesWithStart(String name);

    List<String> getdAllByNameIn(Collection<String> name);
    void deleteAllByNames(String name);

    int updateAllByPrice();
    int updateAllByPrice(BigDecimal Price, Collection<String> list);




}

