package com.example.advquerying.services;

import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface ShampooService {
    List<String> getAllBySizeOrderById(Size size);

    List<String> getAllBySizeOrLabelIdOrderByPrice(Size size, Long labelId);
    List<String> getALLByPriceGT(BigDecimal price);
    int countAllByPriceLessThan(BigDecimal price);

    List<String> getAllByIngredientsNames(Collection<String> names);

    List<String> getAllByIngredientsCount(int count);


}
