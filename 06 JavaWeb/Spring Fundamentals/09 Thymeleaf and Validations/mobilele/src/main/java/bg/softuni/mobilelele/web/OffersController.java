package bg.softuni.mobilelele.web;


import bg.softuni.mobilelele.model.binding.OfferUpdateBindingModel;
import bg.softuni.mobilelele.model.service.OfferUpdateServiceModel;
import bg.softuni.mobilelele.model.view.OfferDetailsView;
import bg.softuni.mobilelele.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OffersController {
    private final OfferService offerService;
    private final ModelMapper modelMapper;

    public OffersController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    //GET
    @GetMapping("/offers/all")
    public String allOffers(Model model) {
        model.addAttribute("offers",
                offerService.getAllOffers());
        return "offers";
    }

    //DETAILS
    @GetMapping("/offers/{id}/details")
    public String showOffer(@PathVariable Long id, Model model) {
        model.addAttribute("offer", this.offerService.findById(id));
        return "details";
    }

    //DELETE
    @DeleteMapping("/offers/{id}")
    public String deleteOffer(@PathVariable Long id) {
        this.offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }

    //UPDATE
    @GetMapping("/offers/{id}/edit")
    public String editOffer(@PathVariable Long id, Model model) {

        OfferDetailsView offerDetailsView = offerService.findById(id);

        OfferUpdateBindingModel offerModel = modelMapper.map(offerDetailsView, OfferUpdateBindingModel.class);

        model.addAttribute("offerModel", offerModel);
        return "update";
    }

    @PatchMapping("/offers/{id}/edit")
    public String editOffer(OfferUpdateBindingModel offerUpdateBindingModel) {
        OfferUpdateServiceModel offerModel = modelMapper.map(offerUpdateBindingModel, OfferUpdateServiceModel.class);

        offerService.updateOffer(offerModel);


        //TODO validation
        return "";
    }


}

