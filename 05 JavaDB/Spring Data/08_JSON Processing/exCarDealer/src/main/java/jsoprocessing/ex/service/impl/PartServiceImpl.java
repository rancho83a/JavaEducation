package jsoprocessing.ex.service.impl;

import com.google.gson.Gson;
import jsoprocessing.ex.constant.GlobalConstant;
import jsoprocessing.ex.model.Dto.PartSeedDto;
import jsoprocessing.ex.model.entity.Part;
import jsoprocessing.ex.repository.PartRepository;
import jsoprocessing.ex.service.PartService;
import jsoprocessing.ex.service.SupplierService;
import jsoprocessing.ex.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PartServiceImpl implements PartService {
    private static final String PART_FILE_NAME = "parts.json";

    private final PartRepository partRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final SupplierService supplierService;

    public PartServiceImpl(PartRepository partRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, SupplierService supplierService) {
        this.partRepository = partRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.supplierService = supplierService;
    }

    @Override
    public void seedParts() throws IOException {
        if (partRepository.count() > 0) {
            return;
        }
        String partJson = Files.readString(Path.of(GlobalConstant.RESOURCE_FILE_PATH + PART_FILE_NAME));

        PartSeedDto[] partSeedDtos = gson.fromJson(partJson, PartSeedDto[].class);
        Arrays.stream(partSeedDtos)
                .filter(validationUtil::isValid)
                .map(d -> {
                    Part part =   modelMapper.map(d, Part.class);
                    part.setSupplier(this.supplierService.getRandomSupplier());
                return part;
                })
                .forEach(partRepository::save);
    }

    @Override
    public Set<Part> getRandomPartSet() {
        Set<Part> partSet = new HashSet<>();

        int randomPartCountSet = ThreadLocalRandom
                .current().nextInt(3, 6);

        for (int i = 0; i < randomPartCountSet; i++) {
            Long randomId = ThreadLocalRandom.current().nextLong(1, this.partRepository.count() + 1);
            partSet.add(this.partRepository.findById(randomId).orElse(null));
        }
        return partSet;
    }

}

