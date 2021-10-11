package jsoprocessing.ex.service;

import jsoprocessing.ex.model.Dto.out.CustomerFullNameBoughtCarsSpentMoneyDto;
import jsoprocessing.ex.model.Dto.out.CustomerIdNameBirthDateIsYoungerDriverDto;
import jsoprocessing.ex.model.entity.Customer;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface CustomerService {
    void seedCustomers() throws IOException;

    Customer getRandomCustomer();
    List<CustomerIdNameBirthDateIsYoungerDriverDto> getAllCustomers();

    List<CustomerFullNameBoughtCarsSpentMoneyDto> getAllCustomerWithCars();

}
