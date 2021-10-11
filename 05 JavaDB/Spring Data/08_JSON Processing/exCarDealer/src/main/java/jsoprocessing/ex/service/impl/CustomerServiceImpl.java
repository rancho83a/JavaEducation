package jsoprocessing.ex.service.impl;

import com.google.gson.Gson;
import jsoprocessing.ex.constant.GlobalConstant;
import jsoprocessing.ex.model.Dto.CustomerSeedDto;
import jsoprocessing.ex.model.Dto.SupplierSeedDto;
import jsoprocessing.ex.model.Dto.out.CustomerFullNameBoughtCarsSpentMoneyDto;
import jsoprocessing.ex.model.Dto.out.CustomerIdNameBirthDateIsYoungerDriverDto;
import jsoprocessing.ex.model.entity.Customer;
import jsoprocessing.ex.model.entity.Part;
import jsoprocessing.ex.model.entity.Supplier;
import jsoprocessing.ex.repository.CustomerRepository;
import jsoprocessing.ex.service.CustomerService;
import jsoprocessing.ex.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String CUSTOMER_FILE_NAME = "customers.json";

    private final CustomerRepository customerRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;


    public CustomerServiceImpl(CustomerRepository customerRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.customerRepository = customerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCustomers() throws IOException {
        if (customerRepository.count() > 0) {
            return;
        }
        String customerJson = Files.readString(Path.of(GlobalConstant.RESOURCE_FILE_PATH + CUSTOMER_FILE_NAME));

        CustomerSeedDto[] customerSeedDtos = gson.fromJson(customerJson, CustomerSeedDto[].class);
        Arrays.stream(customerSeedDtos)
                //.filter(validationUtil::isValid)
                .map(d -> modelMapper.map(d, Customer.class))
                .forEach(customerRepository::save);
    }

    @Override
    public Customer getRandomCustomer() {

        Long randomId = ThreadLocalRandom.current().nextLong(1, this.customerRepository.count()+1 );
        return this.customerRepository.findById(randomId).orElse(null);
    }

    @Override
    public List<CustomerIdNameBirthDateIsYoungerDriverDto> getAllCustomers() {
       return this.customerRepository.findAllByOrderByBirthDateDescYoungDriver()
                .stream()
                .map(c-> modelMapper.map(c,CustomerIdNameBirthDateIsYoungerDriverDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerFullNameBoughtCarsSpentMoneyDto> getAllCustomerWithCars() {
        List<Customer> customersWithCar = this.customerRepository.findAllCustomersWithCar();

        List<CustomerFullNameBoughtCarsSpentMoneyDto> custDto = customersWithCar.stream()
                .map(customer -> {
                    CustomerFullNameBoughtCarsSpentMoneyDto cust = modelMapper.map(customer, CustomerFullNameBoughtCarsSpentMoneyDto.class);
                    cust.setFullName(customer.getName());
                    cust.setBoughtCar(customer.getSales().size());

                    BigDecimal totalSpent = customer.getSales().stream()
                            .map(sale -> {

                                return sale.getCar().getParts().stream()
                                        .map(part -> part.getPrice().multiply(BigDecimal.valueOf(part.getQuantity())))

                                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                                        .multiply(BigDecimal.valueOf(100-sale.getDiscount()))
                                        .multiply(BigDecimal.valueOf(0.01));
                            }).reduce(BigDecimal.ZERO, BigDecimal::add);


                    cust.setSpentMoney(totalSpent.setScale(2, RoundingMode.CEILING));
                    return cust;
                }).collect(Collectors.toList());


        return custDto;
    }
}
