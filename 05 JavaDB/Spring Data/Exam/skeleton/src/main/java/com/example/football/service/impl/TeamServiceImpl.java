package com.example.football.service.impl;

import com.example.football.models.dto.TeamSeedDto;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.service.TeamService;
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
public class TeamServiceImpl implements TeamService {
    private static final String TEAM_FILE_PATH = "src/main/resources/files/json/teams.json";

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final TeamRepository teamRepository;
    private final TownService townService;

    public TeamServiceImpl(Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, TeamRepository teamRepository, TownService townService) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.teamRepository = teamRepository;
        this.townService = townService;
    }


    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files.readString(Path.of(TEAM_FILE_PATH));
    }

    @Override
    public String importTeams() throws IOException {
        StringBuilder sb = new StringBuilder();

        TeamSeedDto[] teamSeedDtos = gson.fromJson(readTeamsFileContent(), TeamSeedDto[].class);


        Arrays.stream(teamSeedDtos)
                .filter(c -> {

                    boolean isValid = validationUtil.isValid(c)
                            && !isEntityExist(c.getName()) // UNIQUE condition
                            && townService.isEntityExist(c.getTownName()); // exist town with name - ПО УСЛОВИЕ Е КАЗАНО,
                    // ЧЕ ЩЕ Е ВАЛИДНО, но не е ясно кое ще е валидно: самото изписване на town или че ще го има в базата,
                    //затова да си направим проверка дали е в базата, няма да попречи

                    sb.append(
                            isValid ? String.format("Successfully imported Team %s - %d", c.getName(), c.getFanBase())
                                    : "Invalid Team"
                    ).append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    Team team = modelMapper.map(dto, Team.class);
                    Town town = this.townService.getTownByName(dto.getTownName());

                    team.setTown(town);

                    return team;
                })
                .forEach(teamRepository::save);

        return sb.toString();
    }

    @Override
    public Team getTeamByName(String name) {
        return this.teamRepository.findFirstByName(name).orElse(null);
    }

    private boolean isEntityExist(String name) {
        return this.teamRepository.existsByName(name);
    }
}
