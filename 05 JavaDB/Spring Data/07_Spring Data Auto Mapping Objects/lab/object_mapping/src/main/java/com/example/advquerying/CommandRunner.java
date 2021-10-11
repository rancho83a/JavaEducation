package com.example.advquerying;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.advquerying.services.EmployeeService;

import java.util.List;

@Component
public class CommandRunner implements CommandLineRunner {
    private final EmployeeService employeeService;

    public CommandRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @Override
    public void run(String... args) throws Exception {
//
//        ManagerDto managerDto = this.employeeService.findOne(1L);
//        System.out.println(managerDto.getFirstName()+" "+ managerDto.getLastName());
//        managerDto.getSubOrdinates().forEach(
//                employeeDto -> {
//                    System.out.println("\t"+employeeDto.getFirstName()+" "+employeeDto.getLastName()+": "+employeeDto.getIncome());
//                }
//        );

        List<ManagerDto> managers = this.employeeService.findAllManagerDto();
        managers.forEach( managerDto -> {
            System.out.println(managerDto.getFirstName()+" "+ managerDto.getLastName()+ " ("+managerDto.getSubOrdinates().size()+ "):");

        managerDto.getSubOrdinates().forEach(
                employeeDto ->
                    System.out.println("\t"+employeeDto.getFirstName()+" "+employeeDto.getLastName()+": "+employeeDto.getIncome()));
                }
        );


    }
}
