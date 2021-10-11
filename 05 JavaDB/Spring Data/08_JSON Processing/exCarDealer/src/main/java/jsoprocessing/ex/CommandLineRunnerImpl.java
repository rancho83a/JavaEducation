package jsoprocessing.ex;

import com.google.gson.Gson;
import jsoprocessing.ex.model.Dto.out.CarIdMAkeModelDistanceDto;
import jsoprocessing.ex.model.Dto.out.CustomerFullNameBoughtCarsSpentMoneyDto;
import jsoprocessing.ex.model.Dto.out.CustomerIdNameBirthDateIsYoungerDriverDto;
import jsoprocessing.ex.model.Dto.out.SupplierIdNamePartCount;
import jsoprocessing.ex.model.Dto.out.task4.CarExportDto;
import jsoprocessing.ex.model.Dto.out.task6.SalesWithAppliedDiscountDto;
import jsoprocessing.ex.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;
    private final BufferedReader reader;
    private final Gson gson;

    public CommandLineRunnerImpl(SupplierService supplierService, PartService partService,
                                 CarService carService, CustomerService customerService, SaleService saleService, Gson gson) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;

        this.saleService = saleService;
        this.gson = gson;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
        System.out.println("Enter task number:");
        switch (reader.readLine()) {
            case "1" -> query1();
            case "2" -> query2();
            case "3" -> query3();
            case "4" -> query4();
            case "5" -> query5();
            case "6" -> query6();

            default -> System.out.println("Enter correct task number!");
        }
    }

    private void query6() {
        List<SalesWithAppliedDiscountDto> salesInfo = this.saleService.getAllSalesWithApplyDiscount();
        System.out.println(gson.toJson(salesInfo));
    }

    private void query5() {

        List<CustomerFullNameBoughtCarsSpentMoneyDto> customers = this.customerService.getAllCustomerWithCars();

        Comparator<CustomerFullNameBoughtCarsSpentMoneyDto> compareBySpentMoney =
                (CustomerFullNameBoughtCarsSpentMoneyDto c1,CustomerFullNameBoughtCarsSpentMoneyDto c2)->
                        c1.getSpentMoney().compareTo(c2.getSpentMoney());

        Comparator<CustomerFullNameBoughtCarsSpentMoneyDto> compareByBoughtCars =
                (CustomerFullNameBoughtCarsSpentMoneyDto c1,CustomerFullNameBoughtCarsSpentMoneyDto c2)->Integer.compare(
                        c1.getBoughtCar(),c2.getBoughtCar());


        customers.sort(compareBySpentMoney.reversed().thenComparing(compareByBoughtCars));

        System.out.println(gson.toJson(customers));


    }

    private void query4() {
        List<CarExportDto> allCars = this.carService.getAllCars();
        System.out.println(gson.toJson(allCars));
    }

    private void query3() {
        List<SupplierIdNamePartCount> localSuppliers = this.supplierService.getAllLocalSuppliers();
        System.out.println(gson.toJson(localSuppliers));
    }


    private void query2() {
        List<CarIdMAkeModelDistanceDto> cars = this.carService.getAllCarsMakeBy("Toyota");
        System.out.println(gson.toJson(cars));
    }

    private void query1() {
        List<CustomerIdNameBirthDateIsYoungerDriverDto> allCustomers = this.customerService.getAllCustomers();
        System.out.println(gson.toJson(allCustomers));
    }

    private void seedData() throws IOException {
        this.supplierService.seedSuppliers();
        this.partService.seedParts();
        this.carService.seedCars();
        this.customerService.seedCustomers();
        this.saleService.seedSales();

    }
}
