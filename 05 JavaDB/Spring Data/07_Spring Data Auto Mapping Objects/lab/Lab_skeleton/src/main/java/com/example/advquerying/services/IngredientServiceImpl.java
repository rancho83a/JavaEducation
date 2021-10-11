package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientServiceImpl implements IngredientService {
private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<String> getAllByNamesWithStart(String name) {
        return this.ingredientRepository.findAllByNameStartingWith(name)
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getdAllByNameIn(Collection<String> name) {
        return this.ingredientRepository.findAllByNameIn(name)
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteAllByNames(String name) {
        this.ingredientRepository.deleteAllByNames(name);
    }

    @Override
    @Transactional
    public int updateAllByPrice() {
        return this.ingredientRepository.updateAllByPrice();

    }
    @Override
    @Transactional
   public int updateAllByPrice(BigDecimal priceChange, Collection<String> names)
   {
    return this.ingredientRepository.updateAllByPrice(priceChange,names);
   }


}
