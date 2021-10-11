package com.example.advquerying.services;

import com.example.advquerying.models.dto.EmployeeCreateRequest;
import com.example.advquerying.models.dto.EmployeeCreateResponce;
import com.example.advquerying.models.dto.EmployeeDto;
import com.example.advquerying.models.dto.ManagerDto;
import com.example.advquerying.models.entities.Employee;
import com.example.advquerying.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public ManagerDto findOne(Long id) {
        //  return EmployeeDto.ofEmployee(this.employeeRepository.findById(id).orElseThrow());

        return this.mapper.map(
                this.employeeRepository.findById(id).orElseThrow(),
                ManagerDto.class
        );
    }

    @Override

    public List<ManagerDto> findAllManagerDto() {

        return this.employeeRepository.findAll().stream()
                .map(e->this.mapper.map(e, ManagerDto.class))
                .collect(Collectors.toList());

//        return this.mapper.map(
//                this.employeeRepository.findAll(),
//                new TypeToken<List<ManagerDto>>() {}.getType()
//        );
    }

    @Override
    public EmployeeCreateResponce save(EmployeeCreateRequest requestDto) {

        Employee entity = this.mapper.map(requestDto, Employee.class);

        this.employeeRepository.save(entity);

        EmployeeCreateResponce response = this.mapper.map(entity, EmployeeCreateResponce.class);
        return  response;

    }
}
