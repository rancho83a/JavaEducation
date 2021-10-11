package com.example.football.service.impl;

import com.example.football.models.dto.StatRootSeedDto;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XMLParse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//ToDo - Implement all methods
@Service
public class StatServiceImpl implements StatService {

    private static final String STAT_FILE_PATH = "src/main/resources/files/xml/stats.xml";

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private StatRepository statRepository;
    private XMLParse xmlParse;

    public StatServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, StatRepository statRepository, XMLParse xmlParse) {

        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.statRepository = statRepository;
        this.xmlParse = xmlParse;
    }

    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(Path.of(STAT_FILE_PATH));
    }

    @Override
    public String importStats() throws JAXBException, FileNotFoundException {

        StringBuilder sb = new StringBuilder();

        StatRootSeedDto statRootSeedDto = xmlParse
                .fromFile(STAT_FILE_PATH, StatRootSeedDto.class);

        statRootSeedDto.getStats()
                .stream()
                .filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && !isEntityExist(dto.getPassing(),dto.getShooting(),  dto.getEndurance());
                    sb.append(
                            isValid ? String.format("Successfully imported Stat %.2f - %.2f - %.2f",
                                    dto.getShooting(), dto.getPassing(),  dto.getEndurance()
                            )
                                    : "Invalid Stat"
                    ).append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    Stat stat = modelMapper.map(dto, Stat.class);

                    return stat;
                })
                .forEach(statRepository::save);

        return sb.toString();
    }

    @Override
    public Stat getStatById(Long id) {

        return this.statRepository.findFirstById(id).orElse(null);
    }

    private boolean isEntityExist(float passing, float shooting, float endurance) {
        return this.statRepository.existsByPassingAndShootingAndEndurance(passing, shooting, endurance);
    }
}
