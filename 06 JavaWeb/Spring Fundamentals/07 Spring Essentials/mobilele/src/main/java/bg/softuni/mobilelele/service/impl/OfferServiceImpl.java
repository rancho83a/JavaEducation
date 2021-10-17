package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.OfferEntity;
import bg.softuni.mobilelele.model.entity.enums.EngineEnum;
import bg.softuni.mobilelele.model.entity.enums.TransmissionEnum;
import bg.softuni.mobilelele.model.view.OffersSummaryView;
import bg.softuni.mobilelele.repository.ModelRepository;
import bg.softuni.mobilelele.repository.OfferRepository;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository, ModelRepository modelRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initializeOffers() {
        if (offerRepository.count() == 0) {
            OfferEntity offer1 = new OfferEntity();
            offer1
                    .setModel(modelRepository.findById(1L).orElse(null))
                    .setEngine(EngineEnum.GASOLINE)
                    .setTransmission(TransmissionEnum.MANUAL)
                    .setMileage(22500)
                    .setPrice(BigDecimal.valueOf(14300))
                    .setYear(2019)
                    .setDescription("Used, but well services and in good condition.")
                    .setSeller(userRepository.findByUsername("pesho")
                            .orElse(null)) // or currentUser.getUserName()
                    .setImageUrl(
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcXp1KBpDKgYs6VqndkBpX8twjPOZbHV86yg&usqp=CAU");

            OfferEntity offer2 = new OfferEntity();
            offer2
                    .setModel(modelRepository.findById(2L).orElse(null))
                    .setEngine(EngineEnum.DIESEL)
                    .setTransmission(TransmissionEnum.AUTOMATIC)
                    .setMileage(209000)
                    .setPrice(BigDecimal.valueOf(5500))
                    .setYear(2000)
                    .setDescription("After full maintenance, insurance, new tires...")
                    .setSeller(userRepository.findByUsername("admin")
                            .orElse(null)) // or currentUser.getUserName()
                    .setImageUrl(
                            "https://www.picclickimg.com/d/l400/pict/283362908243_/FORD-ESCORT-MK5-16L-DOHC-16v-ZETEC.jpg");

            offerRepository.saveAll(List.of(offer1, offer2));
        }
    }

    @Override
    public List<OffersSummaryView> getAllOffers() {

        List<OfferEntity> all = offerRepository.findAll();

        List<OffersSummaryView> collect = offerRepository.findAll().stream()
                .map(this::map)
                .collect(Collectors.toList());


        return collect;
    }

    private OffersSummaryView map(OfferEntity offerEntity){
        OffersSummaryView summaryView = modelMapper.map(offerEntity, OffersSummaryView.class);
        summaryView.setBrand(offerEntity.getModel().getBrand().getName());
        summaryView.setModel(offerEntity.getModel().getName());

        return summaryView;
    }
}
