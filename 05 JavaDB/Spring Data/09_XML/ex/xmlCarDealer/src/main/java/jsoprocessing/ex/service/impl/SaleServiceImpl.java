package jsoprocessing.ex.service.impl;

import jsoprocessing.ex.model.Dto.query6.CarMakeModelDistanceDto;
import jsoprocessing.ex.model.Dto.query6.SaleInfoDto;
import jsoprocessing.ex.model.Dto.query6.SalesRootDto;
import jsoprocessing.ex.model.entity.Customer;
import jsoprocessing.ex.model.entity.Sale;
import jsoprocessing.ex.repository.SalesRepository;
import jsoprocessing.ex.service.CarService;
import jsoprocessing.ex.service.CustomerService;
import jsoprocessing.ex.service.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {
    private final SalesRepository salesRepository;
    private final CarService carService;
    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    public SaleServiceImpl(SalesRepository salesRepository, CarService carService, CustomerService customerService, ModelMapper modelMapper) {
        this.salesRepository = salesRepository;
        this.carService = carService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedSales() {

        for (int i = 0; i < 50; i++) {

            Sale sale = new Sale();
            sale.setCar(this.carService.getRandomCar());

            Customer customer = this.customerService.getRandomCustomer();
            sale.setCustomer(customer);

            int randomDiscount = getRandomDiscount();

            //(Young driver is a driver that
            //has less than 2 years of experience. Those customers get additional 5% off for the sale.).
            if(customer.getYoungDriver()){
                randomDiscount+=5;
            }
            sale.setDiscount(randomDiscount);

            this.salesRepository.save(sale);

            this.salesRepository.save(sale);
        }
    }


    private int getRandomDiscount() {
        int[] discounts = new int[]{0, 5, 10, 15, 20, 30, 40, 50};
        Random r = new Random();
        int randomIndex = r.nextInt(discounts.length);

        return discounts[randomIndex];
    }


    @Override
    public long getCount() {
        return this.salesRepository.count();
    }

    @Override
    public SalesRootDto getAllSalesWithApplyDiscount() {
        SalesRootDto salesRootDto =new SalesRootDto();

        List<SaleInfoDto> sales = this.salesRepository.findAll()
                .stream()
                .map(sale -> {
                    SaleInfoDto saleInfoDto = new SaleInfoDto();

                    CarMakeModelDistanceDto car = modelMapper.map(sale.getCar(), CarMakeModelDistanceDto.class);
                    saleInfoDto.setCar(car);


                    saleInfoDto.setCustomerName(sale.getCustomer().getName());

                    BigDecimal carPrice =
                            sale.getCar().getParts().stream()
                                    .map(part -> part.getPrice().multiply(BigDecimal.valueOf(part.getQuantity())))
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);
                    double discount = 0.01 * sale.getDiscount();

                    BigDecimal carPriceWithDiscount = carPrice.multiply(BigDecimal.valueOf(1 - discount));


                    saleInfoDto.setDiscount(discount);
                    saleInfoDto.setPrice(carPrice);
                    saleInfoDto.setPriceWithDiscount(carPriceWithDiscount);


                    return saleInfoDto;

                }).collect(Collectors.toList());

        salesRootDto.setSales(sales);

        return salesRootDto;
    }
}
