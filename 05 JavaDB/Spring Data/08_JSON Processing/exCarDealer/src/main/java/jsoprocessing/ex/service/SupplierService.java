package jsoprocessing.ex.service;

import jsoprocessing.ex.model.Dto.out.SupplierIdNamePartCount;
import jsoprocessing.ex.model.entity.Supplier;

import java.io.IOException;
import java.util.List;

public interface SupplierService {

    void seedSuppliers() throws IOException;

    Supplier getRandomSupplier();

    List<SupplierIdNamePartCount> getAllLocalSuppliers();
}
