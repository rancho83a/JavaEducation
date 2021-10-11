package com.example.football.service.impl;

import com.example.football.models.dto.TownSeedDto;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


//ToDo - Implement all methods
@Service
public class TownServiceImpl implements TownService {
    private static final String TOWN_FILE_PATH = "src/main/resources/files/json/towns.json";

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final TownRepository townRepository;

    public TownServiceImpl(Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, TownRepository townRepository) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.townRepository = townRepository;
    }


    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWN_FILE_PATH));
    }

    @Override
    public String importTowns() throws IOException {

        StringBuilder sb = new StringBuilder();

        TownSeedDto[] townSeedDtos = gson.fromJson(readTownsFileContent(), TownSeedDto[].class);


        Arrays.stream(townSeedDtos)
                .filter(c -> {

                    boolean isValid = validationUtil.isValid(c) && !isEntityExist(c.getName());

                    sb.append(
                            isValid ? String.format("Successfully imported Town %s - %d", c.getName(), c.getPopulation())
                                    : "Invalid Town"
                    ).append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    Town town = modelMapper.map(dto, Town.class);
                    return town;
                })
                .forEach(townRepository::save);

        return sb.toString();
    }

    @Override
    public boolean isEntityExist(String name) {
        return this.townRepository.existsByName(name);
    }

    @Override
    public Town getTownByName(String name) {
        return this.townRepository.findFirstByName(name).orElse(null);
    }
}
