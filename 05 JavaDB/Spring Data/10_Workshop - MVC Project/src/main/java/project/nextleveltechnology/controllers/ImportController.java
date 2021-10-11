package project.nextleveltechnology.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.nextleveltechnology.model.dto.*;
import project.nextleveltechnology.service.CompanyService;
import project.nextleveltechnology.service.EmployeeService;
import project.nextleveltechnology.service.ProjectService;
import project.nextleveltechnology.util.XMLParse;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class ImportController extends BaseController {
    private final CompanyService companyService;

    private final EmployeeService employeeService;
    private final XMLParse xmlParse;
    private final ProjectService projectService;

    public ImportController(CompanyService companyService, EmployeeService employeeService, XMLParse xmlParse, ProjectService projectService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
        this.xmlParse = xmlParse;
        this.projectService = projectService;

    }

    @GetMapping("/import/xml")
    public String importXml(Model model, HttpServletRequest request) {
        if (!this.isLogged(request)) {
            return "redirect:/";
        }

        model.addAttribute("areImported",
                new boolean[]{this.companyService.exist(), this.projectService.exist(), this.employeeService.exist()});
        return "xml/import-xml";
    }

    @GetMapping("/import/companies")
    public String importCompanies(Model model, HttpServletRequest request) throws IOException {
        if (!this.isLogged(request)) {
            return "redirect:/import/xml";
        }
        model.addAttribute("companies", this.companyService.getXmlToImport());
        return "xml/import-companies";
    }

    @PostMapping("/import/companies")
    public String importCompanies(ImportCompaniesDto dto, HttpServletRequest request) throws JAXBException, FileNotFoundException {
        if (!this.isLogged(request)) {
            return "redirect:/";
        }

        CompanyRootSeedDto companyRoot = this.xmlParse.fromString(dto.getCompanies(), CompanyRootSeedDto.class);
        companyRoot.getCompanies().forEach(this.companyService::create);
        return "redirect:/import/xml/";
    }


    @GetMapping("/import/projects")
    public String importProjects(Model model, HttpServletRequest request) throws IOException {
        if (!this.isLogged(request)) {
            return "redirect:/";
        }
        model.addAttribute("projects", this.projectService.getXmlToImport());
        return "xml/import-projects";
    }


    @PostMapping("/import/projects")
    public String importProjects(ImportProjectsDto dto, HttpServletRequest request) throws JAXBException, FileNotFoundException {

        if (!this.isLogged(request)) {
            return "redirect:/";
        }
        ProjectRootSeedDto projectRoot = this.xmlParse.fromString(dto.getProjects(), ProjectRootSeedDto.class);
        projectRoot.getProjects().forEach(this.projectService::create);
        return "redirect:/import/xml/";
    }



    @GetMapping("/import/employees")
    public String importEmployees(Model model, HttpServletRequest request) throws IOException {
        if (!this.isLogged(request)) {
            return "redirect:/";
        }
        model.addAttribute("employees", this.employeeService.getXmlToImport());
        return "xml/import-employees";
    }

    @PostMapping("/import/employees")
    public String importEmployees(ImportEmployeesDto dto, HttpServletRequest request) throws JAXBException, FileNotFoundException {
        if (!this.isLogged(request)) {
            return "redirect:/";
        }

        EmployeeRootDto employeeRoot = this.xmlParse.fromString(dto.getEmployees(), EmployeeRootDto.class);
        employeeRoot.getEmployees().forEach(this.employeeService::create);
        return "redirect:/import/xml/";
    }


}
