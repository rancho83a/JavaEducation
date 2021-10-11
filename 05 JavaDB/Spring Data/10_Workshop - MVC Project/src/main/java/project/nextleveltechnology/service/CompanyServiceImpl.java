package project.nextleveltechnology.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.nextleveltechnology.model.Entity.Company;
import project.nextleveltechnology.model.dto.CompanySeedDto;
import project.nextleveltechnology.repository.CompanyRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean exist() {
        return this.companyRepository.existsAllBy();
    }

    @Override
    public String getXmlToImport() throws IOException {


        return new String(this.getClass().getClassLoader().getResourceAsStream(FILE_PATH).readAllBytes(),
                StandardCharsets.UTF_8);

    }

    @Override
    public Long create(CompanySeedDto request) {
        Company existing = this.companyRepository.findFirstByName(request.getName());
        if (existing != null) {
            return existing.getId();
        }
        Company company = modelMapper.map(request, Company.class);
        this.companyRepository.save(company);
        return company.getId();
    }

    @Override
    public Company findById(Long id) {
        return this.companyRepository.findById(id).orElseThrow();
    }
}
