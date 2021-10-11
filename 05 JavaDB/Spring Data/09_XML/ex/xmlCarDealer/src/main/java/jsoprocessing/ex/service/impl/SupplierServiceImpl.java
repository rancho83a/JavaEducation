package jsoprocessing.ex.service.impl;

import jsoprocessing.ex.model.Dto.SupplierSeedDto;
import jsoprocessing.ex.model.Dto.query3.SupplierInfoDto;
import jsoprocessing.ex.model.Dto.query3.SupplierRootDto;
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

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public SupplierServiceImpl(SupplierRepository supplierRepository,  ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public Supplier getRandomSupplier() {
        Long randomId = ThreadLocalRandom.current().nextLong(1, this.supplierRepository.count()+1);
        return this.supplierRepository.findById(randomId).orElse(null);
    }
//
//    @Override
//    public List<SupplierIdNamePartCount> getAllLocalSuppliers() {
//        List<Supplier> localSupplier = this.supplierRepository.findAllByIsImporterEqualsNo();
//        return localSupplier.stream()
//                .map(sup->{
//                    SupplierIdNamePartCount supDto = modelMapper.map(sup, SupplierIdNamePartCount.class);
//                    supDto.setPartsCount(sup.getParts().size());
//                    return supDto;
//                })
//                .collect(Collectors.toList());
//    }

    @Override
    public long getProductCount() {
        return this.supplierRepository.count();
    }

    @Override
    public void seedSuppliers(List<SupplierSeedDto> suppliers) {
                    suppliers.stream()
                .filter(validationUtil::isValid)
                .map(d -> modelMapper.map(d, Supplier.class))
                .forEach(supplierRepository::save);

    }

    @Override
    public SupplierRootDto getAllLocalSuppliers() {
        SupplierRootDto supplierRootDto = new SupplierRootDto();
        supplierRootDto.setSuppliers(

                this.supplierRepository.findAllByIsImporterEqualsNo()
                .stream()
                        .map(sup->{

                           SupplierInfoDto supplierInfoDto= modelMapper.map(sup, SupplierInfoDto.class);
                           supplierInfoDto.setPartsCount(sup.getParts().size()+"");

                           return supplierInfoDto;
                        })
                .collect(Collectors.toList())
        );
        return supplierRootDto;
    }

}

