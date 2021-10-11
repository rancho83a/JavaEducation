package jsoprocessing.ex.service.impl;

import jsoprocessing.ex.model.Dto.CustomerSeedDto;
import jsoprocessing.ex.model.Dto.query1.CustomerInfoDto;
import jsoprocessing.ex.model.Dto.query1.CustomerRootDto;
import jsoprocessing.ex.model.Dto.query5.CustomerWithCarInfoDto;
import jsoprocessing.ex.model.Dto.query5.CustomerWithCarRootDto;
import jsoprocessing.ex.model.entity.Customer;
import jsoprocessing.ex.repository.CustomerRepository;
import jsoprocessing.ex.service.CustomerService;
import jsoprocessing.ex.service.PartService;
import jsoprocessing.ex.service.SupplierService;
import jsoprocessing.ex.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final ValidationUtil validationUtil;
    private final SupplierService supplierService;
    private final PartService partService;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(ValidationUtil validationUtil, SupplierService supplierService, PartService partService, ModelMapper modelMapper, CustomerRepository customerRepository) {
        this.validationUtil = validationUtil;
        this.supplierService = supplierService;
        this.partService = partService;
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
    }


    @Override
    public long getCustomersCount() {
        return this.customerRepository.count();
    }

    @Override
    public void seedCustomers(List<CustomerSeedDto> customers) {
        customers.stream()
                .filter(validationUtil::isValid)
                .map(d -> modelMapper.map(d, Customer.class))
                .forEach(customerRepository::save);
    }

    @Override
    public Customer getRandomCustomer() {

        Long randomId = ThreadLocalRandom.current().nextLong(1, this.customerRepository.count() + 1);
        return this.customerRepository.findById(randomId).orElse(null);
    }

    @Override
    public CustomerRootDto getAllCustomers() {

        CustomerRootDto customerRootDto = new CustomerRootDto();

        List<CustomerInfoDto> customerInfo = this.customerRepository.findAllByOrderByBirthDateAscYoungDriver()
                .stream()
                .map(customer ->
                        modelMapper.map(customer, CustomerInfoDto.class))
                .collect(Collectors.toList());


        customerRootDto.setCustomers(customerInfo);
        return customerRootDto;

    }

    @Override
    public CustomerWithCarRootDto getAllCustomerWithCars() {
        CustomerWithCarRootDto customerWithCarRootDto = new CustomerWithCarRootDto();

        List<CustomerWithCarInfoDto> customers = this.customerRepository.findAllCustomersWithCar().stream()
                .map(customer -> {
                    CustomerWithCarInfoDto cust = new CustomerWithCarInfoDto();
                    cust.setName(customer.getName());
                    //cust.setBoughtCars(customer.getSales().size() + "");
                    cust.setBoughtCars(customer.getSales().size());

                    BigDecimal totalSpent = customer.getSales().stream()
                            .map(sale -> sale.getCar().getParts().stream()
                                    .map(part ->
                                            part.getPrice().multiply(BigDecimal.valueOf(part.getQuantity()))
                                                    .multiply(BigDecimal.valueOf(100 - sale.getDiscount()))
                                                    .multiply(BigDecimal.valueOf(0.01))
                                    )
                                    .reduce(BigDecimal.ZERO, BigDecimal::add)).reduce(BigDecimal.ZERO, BigDecimal::add);

                    cust.setSpentMoney(totalSpent.setScale(2, RoundingMode.CEILING));
                    //cust.setSpentMoney(totalSpent.setScale(2, RoundingMode.CEILING) + "");
                    return cust;
                }).collect(Collectors.toList());


        customers.sort(Comparator.comparing(CustomerWithCarInfoDto::getSpentMoney)
                .thenComparing(CustomerWithCarInfoDto::getBoughtCars).reversed());




        customerWithCarRootDto.setCustomers(customers);
        return customerWithCarRootDto;
    }
}
