package com.example.advquerying;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdvqueryingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvqueryingApplication.class, args);
    }

    /*
    Pri "find X" - return info about Manager and his subordinates;
    Pri "findAll" - return Array XML with info about ALL Managers and their subordinates;
    Pri Save info < > </> <> </> - Create object and save it to DB, return new ResponceDto(with id)-> again to XML
    Pri save-from-file - read from XML and save to DB
    Pri findAll to (filePAth) - write like XML to file


     */

}
