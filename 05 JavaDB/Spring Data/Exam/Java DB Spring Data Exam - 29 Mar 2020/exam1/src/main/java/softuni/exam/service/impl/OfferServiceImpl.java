package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Dto.xml.OfferSeedRootDto;
import softuni.exam.models.Dto.xml.SellerSeedRootDto;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.OfferService;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XMLParse;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OfferServiceImpl implements OfferService {

    public static final String OFFER_PATH_NAME = "src/main/resources/files/xml/offers.xml";
    private final ValidationUtil validationUtil;
    private final XMLParse xmlParse;
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final SellerService sellerService;
    private final CarService carService;

    public OfferServiceImpl(ValidationUtil validationUtil, XMLParse xmlParse, OfferRepository offerRepository,
                            ModelMapper modelMapper, SellerService sellerService, CarService carService) {
        this.validationUtil = validationUtil;
        this.xmlParse = xmlParse;
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.sellerService = sellerService;
        this.carService = carService;
    }


    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Path.of(OFFER_PATH_NAME));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {

        StringBuilder sb = new StringBuilder();

        OfferSeedRootDto offerSeedRootDto = xmlParse
                .fromFile(OFFER_PATH_NAME, OfferSeedRootDto.class);

        offerSeedRootDto.getOffers()
                .stream()
                .filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto);
                    sb.append(
                            isValid ? String.format("Successfully import offer %s - %s",
                                    LocalDateTime.parse(dto.getAddedOn(),
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                                    dto.getHasGoldStatus())
                                    : "Invalid offer"
                    ).append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    Offer offer = modelMapper.map(dto, Offer.class);

                    offer.setCar(this.carService.getCarById(dto.getCar().getId()));
                    offer.setSeller(this.sellerService.getSellerById(dto.getSeller().getId()));


                    return offer;
                })
                .forEach(offerRepository::save);

        return sb.toString();
    }

}

