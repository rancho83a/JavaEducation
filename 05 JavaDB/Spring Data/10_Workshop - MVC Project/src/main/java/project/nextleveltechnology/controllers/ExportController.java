package project.nextleveltechnology.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.nextleveltechnology.service.EmployeeService;
import project.nextleveltechnology.service.ProjectService;
import project.nextleveltechnology.util.XMLParse;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/export")
public class ExportController extends BaseController {

    private final ProjectService projectService;
    private final XMLParse xmlParse;
    private final EmployeeService employeeService;

    public ExportController(ProjectService projectService, XMLParse xmlParse, EmployeeService employeeService) {
        this.projectService = projectService;
        this.xmlParse = xmlParse;
        this.employeeService = employeeService;
    }

    @GetMapping("/project-if-finished")

    public String finishedProjects(Model model){
        model.addAttribute("projectsIfFinished",
                this.projectService.finishedProjects()
                .stream().map(xmlParse::serialize)
                .collect(Collectors.joining("\n")
                ));

        return "export/export-project-if-finished";
    }

    @GetMapping("/employees-above")

    public String employeeWithAge(Model model){
        model.addAttribute("employeesAbove",
                this.employeeService.getAllEmployeesAfter25()
                        .stream().map(xmlParse::serialize)
                        .collect(Collectors.joining("\n")
                        ));
        return "export/export-employees-with-age";
    }
}
