package com.example.football.service.impl;

import com.example.football.models.dto.PlayerRootSeedDto;
import com.example.football.models.entity.*;
import com.example.football.repository.PlayerRepository;
import com.example.football.service.PlayerService;
import com.example.football.service.StatService;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XMLParse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

//ToDo - Implement all methods
@Service
public class PlayerServiceImpl implements PlayerService {
    private static final String PLAYER_FILE_PATH = "src/main/resources/files/xml/players.xml";

    private final XMLParse xmlParse;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final TownService townService;
    private final TeamService teamService;
    private final StatService statService;

    public PlayerServiceImpl(XMLParse xmlParse, ModelMapper modelMapper, ValidationUtil validationUtil, TownService townService, TeamService teamService, StatService statService, PlayerRepository playerRepository) {
        this.xmlParse = xmlParse;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.townService = townService;
        this.teamService = teamService;
        this.statService = statService;
        this.playerRepository = playerRepository;
    }

    private PlayerRepository playerRepository;


    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(Path.of(PLAYER_FILE_PATH));
    }

    @Override
    public String importPlayers() throws JAXBException, FileNotFoundException {


        StringBuilder sb = new StringBuilder();

        PlayerRootSeedDto playerRootSeedDto = xmlParse
                .fromFile(PLAYER_FILE_PATH, PlayerRootSeedDto.class);

        playerRootSeedDto.getPlayers()
                .stream()
                .filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && !isEntityExist(dto.getEmail());
                    sb.append(
                            isValid ? String.format("Successfully imported Player %s %s - %s",
                                    dto.getFirstName(), dto.getLastName(), dto.getPosition()
                            )
                                    : "Invalid Player"
                    ).append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    Player player = modelMapper.map(dto, Player.class);

                    Town town = this.townService.getTownByName(dto.getTown().getName());
                    Team team = this.teamService.getTeamByName(dto.getTeam().getName());
                    Stat stat = this.statService.getStatById(dto.getStat().getId());


                    player.setTown(town);
                    player.setTeam(team);
                    player.setStat(stat);
                    return player;
                })
                .forEach(playerRepository::save);

        return sb.toString();


    }

    private boolean isEntityExist(String email) {
        return this.playerRepository.existsByEmail(email);
    }

    @Override
    public String exportBestPlayers() {
//        List<Player> players =this.playerRepository.findAll();
//
//        List<Player> collect = players.stream()
//                .filter(p -> p.getBirthDate().isAfter(LocalDate.of(1995,1,1))
//                && p.getBirthDate().isBefore(LocalDate.of(2003,1,1)))
//                .collect(Collectors.toList());

        LocalDate d1 = LocalDate.of(1995,1,1);
       LocalDate d2 = (LocalDate.of(2003,1,1));
        List<Player> players = this.playerRepository.findAllByBirthDateAndOrderThem(d1, d2);

        StringBuilder sb=new StringBuilder();

        players.forEach(p-> {
            sb.append("Player - " + p.getFirstName()+" "+p.getLastName()+"\n"+
                    "\tPosition - "+p.getPosition()+"\n"+
                    "\tTeam - " + p.getTeam().getName()+"\n"+
                    "\tStadium - " + p.getTeam().getStadiumName()+"\n"
                    );
        });



        return sb.toString();
    }


//    public List<Player> getAllPlayers() {
//        LocalDate d1 = LocalDate.parse("1995-01-01", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//        LocalDate d2 = LocalDate.parse("2003-01-01", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//
//        List<Player> players = this.playerRepository.findAllByBirthDateAndOrderThem(d1,d2);
//
//
//
//        return null;
//    }
}
