package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.service.OfferUpdateServiceModel;
import bg.softuni.mobilelele.model.view.OfferDetailsView;
import bg.softuni.mobilelele.model.view.OffersSummaryView;

import java.util.List;

public interface OfferService {
    void initializeOffers();

    List<OffersSummaryView> getAllOffers();

    OfferDetailsView findById(Long id);
    void deleteOffer(Long id);

    void updateOffer(OfferUpdateServiceModel offerModel);
}
