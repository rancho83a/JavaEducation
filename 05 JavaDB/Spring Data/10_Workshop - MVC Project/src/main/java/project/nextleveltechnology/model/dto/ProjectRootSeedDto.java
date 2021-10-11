package project.nextleveltechnology.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectRootSeedDto {
    @XmlElement(name="project")
    List<ProjectSeedDto> projects;

    public ProjectRootSeedDto() {
    }

    public List<ProjectSeedDto> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectSeedDto> projects) {
        this.projects = projects;
    }
}
