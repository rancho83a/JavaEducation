package jsoprocessing.ex.service;

import jsoprocessing.ex.model.Dto.out.task6.SalesWithAppliedDiscountDto;

import java.util.List;

public interface SaleService {
    void seedSales();

    List<SalesWithAppliedDiscountDto> getAllSalesWithApplyDiscount();

}
