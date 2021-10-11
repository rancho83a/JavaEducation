package com.example.advquerying;


import com.example.advquerying.models.dto.EmployeeCreateRequest;
import com.example.advquerying.models.dto.EmployeeCreateResponce;
import com.example.advquerying.models.dto.ManagerCollectionDto;
import com.example.advquerying.models.dto.ManagerDto;
import com.example.advquerying.services.util.FormatConverter;
import com.example.advquerying.services.util.FormatConverterFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.advquerying.services.EmployeeService;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandRunner implements CommandLineRunner {
    private final EmployeeService employeeService;
    //    private final Gson gson;
//    private final FormatConverter formatConverter;
    private final FormatConverterFactory factory;

    public CommandRunner(EmployeeService employeeService, FormatConverterFactory factory) {
        this.employeeService = employeeService;
//        this.gson = gson;
//        this.formatConverter = formatConverter;
//        this.formatConverter.setPrettyPrint();
        this.factory = factory;
    }


    @Override
    public void run(String... args) throws Exception {
//
//        JAXBContext jaxbContext = JAXBContext.newInstance(ManagerDto.class, ManagerCollectionDto.class,
//                EmployeeCreateRequest.class, EmployeeCreateResponce.class);
//        Marshaller managerMarshaller = jaxbContext.createMarshaller();
//        managerMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//
//
////        JAXBContext jaxbContextManagerCollection = JAXBContext.newInstance(ManagerCollectionDto.class);
////        Marshaller managerCollectionMarshaller = jaxbContextManagerCollection.createMarshaller();
////        managerCollectionMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


        Scanner scan = new Scanner(System.in);
        System.out.println("Enter format Type:");
        String formatType = scan.nextLine();

        FormatConverter converter = factory.create(formatType);
        converter.setPrettyPrint();


        System.out.println("Enter command");
        String input = "";

        while (!"end".equals(input = scan.nextLine())) {

            String[] tokens = input.split(" ", 2);

            switch (tokens[0]) {
                case "find" -> {
                    Long id = Long.parseLong(tokens[1]);
                    ManagerDto managerById = this.employeeService.findOne(id);
//                    String toJson = this.gson.toJson(managerById);
//                    System.out.println(toJson);

                    // System.out.println(this.formatConverter.serialize(managerById));
                    System.out.println(converter.serialize(managerById));
                    //  managerMarshaller.marshal(managerById, System.out);
                }
                case "findAll" -> {
                    List<ManagerDto> allManagerDto = this.employeeService.findAllManagerDto();
                    // System.out.println(this.gson.toJson(allManagerDto));
                    // System.out.println(this.formatConverter.serialize(new ManagerCollectionDto(allManagerDto)));
                    System.out.println(converter.serialize(new ManagerCollectionDto(allManagerDto)));

                    //managerMarshaller.marshal(new ManagerCollectionDto(allManagerDto), System.out);
                }


                //Create DtoRequest from JSON and save it to DB, return new ResponceDto(with id)-> again to JSON
                /*
                save <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <employee first_name="OT2" last_name="2XML :-)"><salary>3000</salary><address>2xmlAddresss2</address></employee>
                *
                 */
                case "save" -> {
                    String inputData = tokens[1];
                    //EmployeeCreateRequest request = gson.fromJson(inputData, EmployeeCreateRequest.class);

//                    EmployeeCreateRequest request = (EmployeeCreateRequest) unmarshaller
//                            .unmarshal(new ByteArrayInputStream(inputData.getBytes()));


                    EmployeeCreateRequest request = converter.deserialize(inputData, EmployeeCreateRequest.class);

                    EmployeeCreateResponce response = this.employeeService.save(request);

                    // System.out.println(this.gson.toJson(response));
                    //managerMarshaller.marshal(response, System.out);
                    // System.out.println(this.formatConverter.serialize(response));
                    System.out.println(converter.serialize(response));
                }
                case "saveFromFile" -> {
                    //   saveFromFile src/main/resources/some-employee.xml

                    String content = Files.readString(Path.of(tokens[1]));

//                    EmployeeCreateRequest fileRequest = this.gson.fromJson(content, EmployeeCreateRequest.class);
//                    //EmployeeCreateRequest fileRequest = this.gson.fromJson(new FileReader(tokens[1]), EmployeeCreateRequest.class);

                    // EmployeeCreateRequest fileRequest = (EmployeeCreateRequest) unmarshaller.unmarshal(new File(tokens[1]));
                    EmployeeCreateRequest fileRequest = converter.deserializeFromFile(tokens[1], EmployeeCreateRequest.class);

                    EmployeeCreateResponce fileResponse = this.employeeService.save(fileRequest);

                    // System.out.println(this.gson.toJson(fileResponse));
                    // managerMarshaller.marshal(fileResponse, System.out);
                    // System.out.println(this.formatConverter.serialize(fileResponse));
                    System.out.println(converter.serialize(fileResponse));

                }
                case "saveAllToFile" -> {
                    // saveAllToFile src/main/resources/output-to-file
//                    FileWriter fw = new FileWriter(tokens[1]);
//                    this.gson.toJson(this.employeeService.findAllManagerDto(),
//                            fw);
//                    fw.flush();
//                    fw.close();

                    List<ManagerDto> managers = this.employeeService.findAllManagerDto();
//                        this.gson.toJson(managers,
//                                fw1);

//                        managerMarshaller.marshal(
//                                new ManagerCollectionDto(managers),
//                                new File(tokens[1] + ".xml")
//                        );
                    converter.serialize(new ManagerCollectionDto(managers),
                            tokens[1] + "." + formatType);

                    System.out.println("Written to file " + tokens[1]);
                }
                case "change-format" -> {
                    converter=this.factory.create(tokens[1]);
                    System.out.println("Format has changed to "+tokens[1]);
                }
                case "pretty-print" -> {
                    converter.setPrettyPrint();
                    System.out.println("Property 'prettyPtint' is set");
                }
            }
        }
    }
}

