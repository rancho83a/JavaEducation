package project.nextleveltechnology.service;

import project.nextleveltechnology.model.Entity.Company;
import project.nextleveltechnology.model.dto.CompanyRootSeedDto;
import project.nextleveltechnology.model.dto.CompanySeedDto;

import java.io.IOException;

public interface CompanyService {
    String FILE_PATH = "files/xmls/companies.xml";
    boolean exist();
    String getXmlToImport() throws IOException;

    Long create(CompanySeedDto request);

    Company findById(Long id);
}
