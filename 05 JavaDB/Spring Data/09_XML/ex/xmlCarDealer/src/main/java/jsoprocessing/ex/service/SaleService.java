package jsoprocessing.ex.service;

import jsoprocessing.ex.model.Dto.query6.SalesRootDto;

public interface SaleService {
    void seedSales();

    long getCount();

    SalesRootDto getAllSalesWithApplyDiscount();
}
