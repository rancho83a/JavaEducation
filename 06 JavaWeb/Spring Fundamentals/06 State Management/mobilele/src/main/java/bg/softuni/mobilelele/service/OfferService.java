package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.view.OffersSummaryView;

import java.util.List;

public interface OfferService {
    void initializeOffers();

    List<OffersSummaryView> getAllOffers();
}
