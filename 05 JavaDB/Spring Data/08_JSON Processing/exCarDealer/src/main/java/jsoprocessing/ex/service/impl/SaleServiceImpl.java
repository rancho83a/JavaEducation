package jsoprocessing.ex.service.impl;

import jsoprocessing.ex.model.Dto.out.task6.CarMakeModelTravelledDistanceDto;
import jsoprocessing.ex.model.Dto.out.task6.SalesWithAppliedDiscountDto;
import jsoprocessing.ex.model.entity.Customer;
import jsoprocessing.ex.model.entity.Sale;
import jsoprocessing.ex.repository.SalesRepository;
import jsoprocessing.ex.service.CarService;
import jsoprocessing.ex.service.CustomerService;
import jsoprocessing.ex.service.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
        if (this.salesRepository.count() > 0) {
            return;
        }

        for (int i = 0; i < 50; i++) {

            Sale sale = new Sale();
            sale.setCar(this.carService.getRandomCar());

            Customer customer = this.customerService.getRandomCustomer();
            sale.setCustomer(customer);

            int randomDiscount = getRandomDiscount();

            if(customer.getYoungDriver()){
                randomDiscount+=5;
            }
            sale.setDiscount(randomDiscount);

            this.salesRepository.save(sale);
        }
    }

    @Override
    public List<SalesWithAppliedDiscountDto> getAllSalesWithApplyDiscount() {
        List<Sale> sales = this.salesRepository.findAll();
        List<SalesWithAppliedDiscountDto> salesOut = sales.stream()
                .map(sale -> {


                    SalesWithAppliedDiscountDto saleDto = new SalesWithAppliedDiscountDto();
//                    CarMakeModelTravelledDistanceDto car = new CarMakeModelTravelledDistanceDto();
//                    car.setMake(sale.getCar().getMake());
//                    car.setModel(sale.getCar().getModel());
//                    car.setTravelledDistance(sale.getCar().getTravelledDistance());

                    CarMakeModelTravelledDistanceDto car = modelMapper.map(sale.getCar(), CarMakeModelTravelledDistanceDto.class);

                    BigDecimal carPrice =
                            sale.getCar().getParts().stream()
                                    .map(part -> part.getPrice().multiply(BigDecimal.valueOf(part.getQuantity())))

                                    .reduce(BigDecimal.ZERO, BigDecimal::add);
                    double discount = 0.01 * sale.getDiscount();

                    BigDecimal carPriceWithDiscount=carPrice.multiply(BigDecimal.valueOf(1 - discount));
                    saleDto.setCar(car);
                    saleDto.setCustomerName(sale.getCustomer().getName());
                    saleDto.setDiscount(discount);
                   // saleDto.setPrice(carPrice.setScale(2, BigDecimal.ROUND_CEILING));
                    saleDto.setPrice(carPrice.setScale(2));
                    saleDto.setPriceWithDiscount(carPriceWithDiscount.setScale(2, RoundingMode.CEILING));

                    return saleDto;
                }).collect(Collectors.toList());

        return salesOut;
    }



    private int getRandomDiscount() {
        int[] discounts = new int[]{0, 5, 10, 15, 20, 30, 40, 50};
        Random r = new Random();
        int randomIndex = r.nextInt(discounts.length);

        return discounts[randomIndex];
    }
}
