package com.example.advquerying.services;

import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ShampooServiceImpl implements ShampooService {
    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<String> getAllBySizeOrderById(Size size) {
        return this.shampooRepository.findAllBySizeOrderById(size)
                .stream()
                .map(s -> String.format("%s %s %.2flv.", s.getBrand(), size, s.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBySizeOrLabelIdOrderByPrice(Size size, Long labelId) {
        return this.shampooRepository.findAllBySizeOrLabelIdOrderByPrice(size, labelId)
                .stream()
                .map(s -> String.format("%s %s %.2flv.", s.getBrand(), size, s.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getALLByPriceGT(BigDecimal price) {
        return this.shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(price)
                .stream()
                .map(s -> String.format("%s %s %.2flv.", s.getBrand(), s.getSize(), s.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public int countAllByPriceLessThan(BigDecimal price) {
        return this.shampooRepository.countAllByPriceLessThan(price);
    }

    @Override
    public List<String> getAllByIngredientsNames(Collection<String> names) {
        return this.shampooRepository.findAllByIngredientsNames(names).stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllByIngredientsCount(int count) {
        return this.shampooRepository.findAllByIngredientsCount(count)
                .stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.toList());
    }
}
