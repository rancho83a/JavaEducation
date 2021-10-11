package project.nextleveltechnology.service;

import project.nextleveltechnology.model.Entity.Project;
import project.nextleveltechnology.model.dto.ExportedProjectDto;
import project.nextleveltechnology.model.dto.ProjectSeedDto;

import java.io.IOException;
import java.util.List;

public interface ProjectService {
    String FILE_PATH = "files/xmls/projects.xml";

    boolean exist();
    String getXmlToImport() throws IOException;
    Long create (ProjectSeedDto request);

    Project find(Long id);

    List<ExportedProjectDto> finishedProjects();

}
