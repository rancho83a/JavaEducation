package jsoprocessing.ex;

import jsoprocessing.ex.model.Dto.CarRootSeedDto;
import jsoprocessing.ex.model.Dto.CustomerRootSeedDto;
import jsoprocessing.ex.model.Dto.PartRootSeedDto;
import jsoprocessing.ex.model.Dto.SupplierRootSeedDto;
import jsoprocessing.ex.model.Dto.query1.CustomerRootDto;
import jsoprocessing.ex.model.Dto.query2.CarRootDto;
import jsoprocessing.ex.model.Dto.query3.SupplierRootDto;
import jsoprocessing.ex.model.Dto.query4.CarInfoWithPartsDto;
import jsoprocessing.ex.model.Dto.query4.CarWitPartsRootDto;
import jsoprocessing.ex.model.Dto.query5.CustomerWithCarRootDto;
import jsoprocessing.ex.model.Dto.query6.SalesRootDto;
import jsoprocessing.ex.util.*;

import jsoprocessing.ex.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private static final String RESOURCE_PATH = "src/main/resources/files/";
    private static final String OUTPUT_PATH = "src/main/resources/files/out/";
    private static final String SUPPLIER_FILE_NAME = "suppliers.xml";
    private static final String CAR_FILE_NAME = "cars.xml";
    private static final String CUSTOMER_FILE_NAME = "customers.xml";
    private static final String PART_FILE_NAME = "parts.xml";
    private static final String QUERY1_FILE_NAME = "query1.xml";
    private static final String QUERY2_FILE_NAME = "query2.xml";
    private static final String QUERY3_FILE_NAME = "query3.xml";
    private static final String QUERY4_FILE_NAME = "query4.xml";
    private static final String QUERY5_FILE_NAME = "query5.xml";
    private static final String QUERY6_FILE_NAME = "query6.xml";


    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;
    private final BufferedReader reader;
    private final XMLParse xmlParse;


    public CommandLineRunnerImpl(SupplierService supplierService, PartService partService,
                                 CarService carService, CustomerService customerService, SaleService saleService, XMLParse xmlParse) {
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.xmlParse = xmlParse;
        this.supplierService = supplierService;
        this.partService = partService;

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

    private void query6() throws JAXBException {
        SalesRootDto salesRootDto = this.saleService.getAllSalesWithApplyDiscount();
        xmlParse.writeToFile(OUTPUT_PATH + QUERY6_FILE_NAME, salesRootDto);

    }

    private void query5() throws JAXBException {

        CustomerWithCarRootDto customerWithCarRootDto = this.customerService.getAllCustomerWithCars();
        xmlParse.writeToFile(OUTPUT_PATH + QUERY5_FILE_NAME, customerWithCarRootDto);

    }

    private void query4() throws JAXBException {
        CarWitPartsRootDto carWitPartsRootDto = this.carService.getAllCars();
        xmlParse.writeToFile(OUTPUT_PATH + QUERY4_FILE_NAME, carWitPartsRootDto);


    }

    private void query3() throws JAXBException {
        SupplierRootDto supplierRootDto = this.supplierService.getAllLocalSuppliers();
        xmlParse.writeToFile(OUTPUT_PATH + QUERY3_FILE_NAME, supplierRootDto);

    }


    private void query2() throws JAXBException {
        CarRootDto carRootDto = this.carService.getAllCarsMakeBy("Toyota");
        xmlParse.writeToFile(OUTPUT_PATH + QUERY2_FILE_NAME, carRootDto);

    }

    private void query1() throws JAXBException {
        CustomerRootDto customerRootDto = this.customerService.getAllCustomers();
        xmlParse.writeToFile(OUTPUT_PATH + QUERY1_FILE_NAME, customerRootDto);

    }


    private void seedData() throws JAXBException, FileNotFoundException {
        seedSuppliers();
        seedParts();
        seedCars();
        seedCustomers();
        seedSales();
    }

    private void seedSales() {
        if (this.saleService.getCount() > 0) {
            return;
        }
        this.saleService.seedSales();

    }

    private void seedCustomers() throws JAXBException, FileNotFoundException {
        if (this.customerService.getCustomersCount() > 0) {
            return;
        }
        CustomerRootSeedDto customerRootSeedDto = xmlParse.fromFile(RESOURCE_PATH + CUSTOMER_FILE_NAME, CustomerRootSeedDto.class);
        this.customerService.seedCustomers(customerRootSeedDto.getCustomers());
    }

    private void seedCars() throws JAXBException, FileNotFoundException {
        if (this.carService.getCarsCount() > 0) {
            return;
        }
        CarRootSeedDto carRootSeedDto = xmlParse.fromFile(RESOURCE_PATH + CAR_FILE_NAME, CarRootSeedDto.class);
        this.carService.seedCars(carRootSeedDto.getCars());
    }

    private void seedParts() throws JAXBException, FileNotFoundException {
        if (this.partService.getProductCount() > 0) {
            return;
        }
        PartRootSeedDto partRootSeedDto = xmlParse.fromFile(RESOURCE_PATH + PART_FILE_NAME, PartRootSeedDto.class);
        this.partService.seedParts(partRootSeedDto.getParts());
    }

    private void seedSuppliers() throws JAXBException, FileNotFoundException {
        if (this.supplierService.getProductCount() > 0) {
            return;
        }
        SupplierRootSeedDto supplierRootSeedDto = xmlParse.fromFile(RESOURCE_PATH + SUPPLIER_FILE_NAME, SupplierRootSeedDto.class);
        this.supplierService.seedSuppliers(supplierRootSeedDto.getSuppliers());
    }
}
