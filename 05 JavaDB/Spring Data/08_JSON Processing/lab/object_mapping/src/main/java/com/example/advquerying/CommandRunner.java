package com.example.advquerying;


import com.example.advquerying.models.dto.EmployeeCreateRequest;
import com.example.advquerying.models.dto.EmployeeCreateResponce;
import com.example.advquerying.models.dto.ManagerDto;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.advquerying.services.EmployeeService;

import java.io.FileReader;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandRunner implements CommandLineRunner {
    private final EmployeeService employeeService;
    private final Gson gson;

    public CommandRunner(EmployeeService employeeService, Gson gson) {
        this.employeeService = employeeService;
        this.gson = gson;
    }


    @Override
    public void run(String... args) throws Exception {

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter command");
        String input="";

        while(!"end".equals(input=scan.nextLine())){

            String[] tokens = input.split(" ", 2);

            switch (tokens[0]){
                case "find"-> {
                    Long id = Long.parseLong(tokens[1]);
                    ManagerDto managerById = this.employeeService.findOne(id);
                    String toJson = this.gson.toJson(managerById);
                    System.out.println(toJson);
                }
                case "findAll" -> {
                    List<ManagerDto> allManagerDto = this.employeeService.findAllManagerDto();
                    System.out.println(this.gson.toJson(allManagerDto));
                }
                //Create DtoRequest from JSON and save it to DB, return new ResponceDto(with id)-> again to JSON
                case "save" -> {
                    String json = tokens[1];
                    EmployeeCreateRequest request = gson.fromJson(json, EmployeeCreateRequest.class);
                    EmployeeCreateResponce response = this.employeeService.save(request);
                    System.out.println(this.gson.toJson(response));
                }
                case "saveFromFile" ->{

                    String content = Files.readString(Path.of(tokens[1]));

                    EmployeeCreateRequest request = this.gson.fromJson(content, EmployeeCreateRequest.class);
                    //EmployeeCreateRequest request = this.gson.fromJson(new FileReader(tokens[1]), EmployeeCreateRequest.class);
                    EmployeeCreateResponce response = this.employeeService.save(request);
                    System.out.println(this.gson.toJson(response));
                }
                case "saveAllToFile" -> {
                    FileWriter fw = new FileWriter(tokens[1]);
                    this.gson.toJson(this.employeeService.findAllManagerDto(),
                            fw);
                    fw.flush();
                    fw.close();
                    System.out.println("Written to file "+tokens[1]);
                }
            }
        }
//
//        ManagerDto managerDto = this.employeeService.findOne(1L);
//        System.out.println(managerDto.getFirstName()+" "+ managerDto.getLastName());
//        managerDto.getSubOrdinates().forEach(
//                employeeDto -> {
//                    System.out.println("\t"+employeeDto.getFirstName()+" "+employeeDto.getLastName()+": "+employeeDto.getIncome());
//                }
//        );
//
//        List<ManagerDto> managers = this.employeeService.findAllManagerDto();
//        managers.forEach( managerDto -> {
//            System.out.println(managerDto.getFirstName()+" "+ managerDto.getLastName()+ " ("+managerDto.getSubOrdinates().size()+ "):");
//
//        managerDto.getSubOrdinates().forEach(
//                employeeDto ->
//                    System.out.println("\t"+employeeDto.getFirstName()+" "+employeeDto.getLastName()+": "+employeeDto.getIncome()));
//                }
//        );


    }
}
