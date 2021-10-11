package com.example.advquerying.configs;

import com.example.advquerying.models.dto.EmployeeDto;
import com.example.advquerying.models.entities.Employee;
import org.modelmapper.ModelMapper;

import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMappingConfig {
    @Bean
    public ModelMapper modelMapper() {

       ModelMapper mapper = new ModelMapper();
//        mapper.addMappings(new PropertyMap<Employee, EmployeeDto>() {
//            @Override
//            protected void configure() {
//                map().setIncome(source.getSalary());
//            }
//        });
//
        TypeMap<Employee, EmployeeDto> typeMap = mapper.createTypeMap(Employee.class, EmployeeDto.class);
        typeMap.addMappings(mapping-> mapping.map(Employee::getSalary, EmployeeDto::setIncome));

        return mapper;
    }

}
