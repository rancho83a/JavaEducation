package jsoprocessing.ex.service;

import jsoprocessing.ex.model.Dto.CustomerSeedDto;
import jsoprocessing.ex.model.Dto.query1.CustomerRootDto;
import jsoprocessing.ex.model.Dto.query5.CustomerWithCarRootDto;
import jsoprocessing.ex.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    long getCustomersCount();

    void seedCustomers(List<CustomerSeedDto> customers);

    Customer getRandomCustomer();

    CustomerRootDto getAllCustomers();

    CustomerWithCarRootDto getAllCustomerWithCars();
}
