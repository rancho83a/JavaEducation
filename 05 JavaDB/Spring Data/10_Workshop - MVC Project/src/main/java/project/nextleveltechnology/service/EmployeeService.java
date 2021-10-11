package project.nextleveltechnology.service;

import project.nextleveltechnology.model.Entity.Employee;
import project.nextleveltechnology.model.dto.EmployeeSeedDto;
import project.nextleveltechnology.model.dto.ExportedEmployeeDto;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {
    String FILE_PATH = "files/xmls/employees.xml";

    boolean exist();
    String getXmlToImport() throws IOException;
    Long create(EmployeeSeedDto request);

    List<ExportedEmployeeDto> getAllEmployeesAfter25();


}
