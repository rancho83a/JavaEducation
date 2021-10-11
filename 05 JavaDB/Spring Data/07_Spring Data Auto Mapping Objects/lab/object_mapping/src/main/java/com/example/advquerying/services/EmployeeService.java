package com.example.advquerying.services;

import com.example.advquerying.models.dto.EmployeeDto;
import com.example.advquerying.models.dto.ManagerDto;

import java.util.List;

public interface EmployeeService {
    ManagerDto findOne(Long id);
    //EmployeeDto findOne(Long id);

    List<ManagerDto> findAllManagerDto();
}
