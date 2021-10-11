package jsoprocessing.ex.service.impl;

import com.google.gson.Gson;
import jsoprocessing.ex.constant.GlobalConstant;
import jsoprocessing.ex.model.Dto.SupplierSeedDto;
import jsoprocessing.ex.model.Dto.out.SupplierIdNamePartCount;
import jsoprocessing.ex.model.entity.Supplier;
import jsoprocessing.ex.repository.SupplierRepository;
import jsoprocessing.ex.service.SupplierService;
import jsoprocessing.ex.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private static final String SUPPLIER_FILE_NAME = "suppliers.json";

    private final SupplierRepository supplierRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public SupplierServiceImpl(SupplierRepository supplierRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.supplierRepository = supplierRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedSuppliers() throws IOException {
        if (supplierRepository.count() > 0) {
            return;
        }
        String supplierJson = Files.readString(Path.of(GlobalConstant.RESOURCE_FILE_PATH + SUPPLIER_FILE_NAME));

        SupplierSeedDto[] supplierSeedDto = gson.fromJson(supplierJson, SupplierSeedDto[].class);
        Arrays.stream(supplierSeedDto)
                .filter(validationUtil::isValid)
                .map(d -> modelMapper.map(d, Supplier.class))
                .forEach(supplierRepository::save);
    }

    @Override
    public Supplier getRandomSupplier() {
        Long randomId = ThreadLocalRandom.current().nextLong(1, this.supplierRepository.count()+1);
        return this.supplierRepository.findById(randomId).orElse(null);
    }

    @Override
    public List<SupplierIdNamePartCount> getAllLocalSuppliers() {
        List<Supplier> localSupplier = this.supplierRepository.findAllByIsImporterEqualsNo();
        return localSupplier.stream()
                .map(sup->{
                    SupplierIdNamePartCount supDto = modelMapper.map(sup, SupplierIdNamePartCount.class);
                    supDto.setPartsCount(sup.getParts().size());
                    return supDto;
                })
                .collect(Collectors.toList());
    }

}

