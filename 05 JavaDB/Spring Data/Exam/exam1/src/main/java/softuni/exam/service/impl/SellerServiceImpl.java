package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Dto.xml.SellerSeedRootDto;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XMLParse;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SellerServiceImpl implements SellerService {
    public static final String SELLERS_PATH_NAME = "src/main/resources/files/xml/sellers.xml";
    private final ValidationUtil validationUtil;
    private final XMLParse xmlParse;
    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;

    public SellerServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, XMLParse xmlParse, SellerRepository sellerRepository) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParse = xmlParse;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(Path.of(SELLERS_PATH_NAME));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        StringBuilder sb =new StringBuilder();

        SellerSeedRootDto sellerSeedRootDto = xmlParse.fromFile(SELLERS_PATH_NAME,
                SellerSeedRootDto.class);

        sellerSeedRootDto.getSellers().stream()
                .filter( dto->{
        boolean isValid = validationUtil.isValid(dto);
                    sb.append(
                            isValid ? String.format("Successfully import seller %s - %s",
                                    dto.getFirstName(),dto.getEmail())
                                    : "Invalid seller"
                    ).append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> modelMapper.map(dto, Seller.class))
                .forEach(sellerRepository::save);

        return sb.toString();
    }

    @Override
    public Seller getSellerById(Long id) {
        return this.sellerRepository.findById(id).orElse(null);
    }
}
