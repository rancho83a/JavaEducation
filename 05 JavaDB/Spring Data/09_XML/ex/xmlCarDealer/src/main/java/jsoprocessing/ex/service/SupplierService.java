package jsoprocessing.ex.service;

import jsoprocessing.ex.model.Dto.SupplierSeedDto;
import jsoprocessing.ex.model.Dto.query3.SupplierRootDto;
import jsoprocessing.ex.model.entity.Supplier;

import java.io.IOException;
import java.util.List;

public interface SupplierService {


    Supplier getRandomSupplier();

  //  List<SupplierIdNamePartCount> getAllLocalSuppliers();

    long getProductCount();

    void seedSuppliers(List<SupplierSeedDto> suppliers);

    SupplierRootDto getAllLocalSuppliers();
}
