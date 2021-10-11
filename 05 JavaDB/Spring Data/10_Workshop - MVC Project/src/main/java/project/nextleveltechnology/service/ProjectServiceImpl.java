package project.nextleveltechnology.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.nextleveltechnology.model.Entity.Company;
import project.nextleveltechnology.model.Entity.Project;
import project.nextleveltechnology.model.dto.ExportedProjectDto;
import project.nextleveltechnology.model.dto.ProjectSeedDto;
import project.nextleveltechnology.repository.ProjectRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final CompanyService companyService;

    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper, CompanyService companyService) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.companyService = companyService;
    }

    @Override
    public boolean exist() {
        return this.projectRepository.existsAllBy();
    }

    @Override
    public String getXmlToImport() throws IOException {


        return new String(this.getClass().getClassLoader().getResourceAsStream(FILE_PATH).readAllBytes(),
                StandardCharsets.UTF_8);
    }

    @Override
    public Long create(ProjectSeedDto request) {
        Project existing = this.projectRepository.findFirstByNameAndCompanyName(request.getName(), request.getCompany().getName());

        if(existing!=null){
            return existing.getId();
        }

        Long companyId = this.companyService.create(request.getCompany());
        Company company = this.companyService.findById(companyId);

        Project project = modelMapper.map(request, Project.class);
        project.setCompany(company);

        this.projectRepository.save(project);
        return project.getId();
    }

    @Override
    public Project find(Long id) {
        return this.projectRepository.findById(id).orElseThrow();
    }

    @Override
    public List<ExportedProjectDto> finishedProjects() {
        List<ExportedProjectDto> projects = this.projectRepository.findAllByFinishedIsTrue()
                .stream()
                .map(p -> modelMapper.map(p, ExportedProjectDto.class))
                .collect(Collectors.toList());


        return projects;
    }
}
