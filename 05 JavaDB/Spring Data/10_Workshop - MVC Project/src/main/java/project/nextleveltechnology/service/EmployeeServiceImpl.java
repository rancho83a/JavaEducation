package project.nextleveltechnology.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.nextleveltechnology.model.Entity.Employee;
import project.nextleveltechnology.model.Entity.Project;
import project.nextleveltechnology.model.dto.EmployeeSeedDto;
import project.nextleveltechnology.model.dto.ExportedEmployeeDto;
import project.nextleveltechnology.repository.EmployeeRepository;
import project.nextleveltechnology.util.XMLParse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final ProjectService projectService;
    private final CompanyService companyService;
    private  final XMLParse xmlParse;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper, ProjectService projectService, CompanyService companyService, XMLParse xmlParse) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.projectService = projectService;
        this.companyService = companyService;

        this.xmlParse = xmlParse;
    }

    @Override
    public boolean exist() {
        return this.employeeRepository.existsAllBy();
    }

    @Override
    public String getXmlToImport() throws IOException {
        return new String(this.getClass().getClassLoader().getResourceAsStream(FILE_PATH).readAllBytes(),
                StandardCharsets.UTF_8);

    }

    @Override
    public Long create(EmployeeSeedDto request) {

        Employee existing = this.employeeRepository.findFirstByFirstNameAndLastNameAndAge(
                request.getFirstName(),
                request.getLastName(),
                request.getAge()
        );
        if(existing!=null){
            return existing.getId();
        }

        Employee employee = modelMapper.map(request, Employee.class);
        Long projectId = this.projectService.create(request.getProject());
        Project project = this.projectService.find(projectId);
        employee.setProject(project);

        this.employeeRepository.save(employee);


        return employee.getId();
    }

    @Override
    public List<ExportedEmployeeDto> getAllEmployeesAfter25() {
        List<ExportedEmployeeDto> employees = this.employeeRepository.findAllByAgeGreaterThan(25)
                .stream()
                .map(e-> modelMapper.map(e, ExportedEmployeeDto.class))
                .collect(Collectors.toList());
        return employees;
    }
}
