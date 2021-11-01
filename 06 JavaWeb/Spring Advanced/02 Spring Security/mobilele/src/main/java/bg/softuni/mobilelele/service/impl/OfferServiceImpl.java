package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.binding.OfferAddBindModel;
import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.model.entity.OfferEntity;
import bg.softuni.mobilelele.model.entity.enums.EngineEnum;
import bg.softuni.mobilelele.model.entity.enums.TransmissionEnum;
import bg.softuni.mobilelele.model.service.OfferAddServiceModel;
import bg.softuni.mobilelele.model.service.OfferUpdateServiceModel;
import bg.softuni.mobilelele.model.view.OfferDetailsView;
import bg.softuni.mobilelele.model.view.OffersSummaryView;
import bg.softuni.mobilelele.repository.ModelRepository;
import bg.softuni.mobilelele.repository.OfferRepository;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.service.OfferService;
import bg.softuni.mobilelele.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
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
                    .setSeller(userRepository.findByUsername("user")
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
                .map(this::mapToSummaryView)
                .collect(Collectors.toList());


        return collect;
    }

    @Override
    public OfferDetailsView findById(Long id) {
        OfferEntity offerEntity = offerRepository.findById(id).get();
        OfferDetailsView offerDetails = mapToDetailView(offerEntity);

        return offerDetails;
    }

    @Override
    public void deleteOffer(Long id) {
        this.offerRepository.deleteById(id);
    }

    @Override
    public void updateOffer(OfferUpdateServiceModel offerModel) {
        OfferEntity offerEntity = offerRepository.findById(offerModel.getId()).orElseThrow(() ->
                new ObjectNotFoundException("Object with" + offerModel.getId() + " not found!"));

        offerEntity.setPrice(offerModel.getPrice())
                .setDescription(offerModel.getDescription())
                .setEngine(offerModel.getEngine())
                .setImageUrl(offerModel.getImageUrl())
                .setMileage(offerModel.getMileage())
                .setYear(offerModel.getYear())
                .setTransmission(offerModel.getTransmission());

        offerRepository.save(offerEntity);

    }

    private OfferDetailsView mapToDetailView(OfferEntity offerEntity) {
        OfferDetailsView offerDetails = modelMapper.map(offerEntity, OfferDetailsView.class);
        offerDetails.setModel(offerEntity.getModel().getName());
        offerDetails.setBrand(offerEntity.getModel().getBrand().getName());
        offerDetails.setSellerFullName(offerEntity.getSeller().getFirstName() + " " + offerEntity.getSeller().getLastName());

        return offerDetails;
    }

    private OffersSummaryView mapToSummaryView(OfferEntity offerEntity) {
        OffersSummaryView summaryView = modelMapper.map(offerEntity, OffersSummaryView.class);
        summaryView.setModel(offerEntity.getModel().getName());
        summaryView.setBrand(offerEntity.getModel().getBrand().getName());

        return summaryView;
    }
    @Override
    public OfferAddServiceModel addOffer(OfferAddBindModel offerAddBindModel) {
        OfferAddServiceModel offerAddServiceModel = modelMapper.map(offerAddBindModel, OfferAddServiceModel.class);
        OfferEntity newOffer = modelMapper.map(offerAddServiceModel, OfferEntity.class);
        newOffer.setCreated(Instant.now());
        newOffer.setSeller(userRepository.findByUsername(currentUser.getUserName()).orElseThrow());
        ModelEntity model = modelRepository.getById(offerAddBindModel.getModelId());
        newOffer.setModel(model);

        OfferEntity savedOffer = offerRepository.save(newOffer);
        return modelMapper.map(savedOffer, OfferAddServiceModel.class);
    }
}
