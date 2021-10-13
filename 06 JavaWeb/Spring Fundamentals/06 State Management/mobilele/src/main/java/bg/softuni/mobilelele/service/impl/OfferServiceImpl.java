package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.OfferEntity;
import bg.softuni.mobilelele.model.view.OffersSummaryView;
import bg.softuni.mobilelele.repository.OfferRepository;
import bg.softuni.mobilelele.service.OfferService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public void initializeOffers() {
        //TODO
    }

    @Override
    public List<OffersSummaryView> getAllOffers() {


        return offerRepository.findAll().stream()
                .map(this::map).collect(Collectors.toList());
    }

    private OffersSummaryView map(OfferEntity offerEntity){
        //TODO

        return new OffersSummaryView();
    }
}
