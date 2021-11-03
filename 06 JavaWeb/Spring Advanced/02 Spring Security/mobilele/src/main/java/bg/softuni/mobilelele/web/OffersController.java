package bg.softuni.mobilelele.web;


import bg.softuni.mobilelele.model.binding.OfferAddBindModel;
import bg.softuni.mobilelele.model.binding.OfferUpdateBindingModel;
import bg.softuni.mobilelele.model.entity.enums.EngineEnum;
import bg.softuni.mobilelele.model.entity.enums.TransmissionEnum;
import bg.softuni.mobilelele.model.service.OfferAddServiceModel;
import bg.softuni.mobilelele.model.service.OfferUpdateServiceModel;
import bg.softuni.mobilelele.model.view.OfferDetailsView;
import bg.softuni.mobilelele.service.BrandService;
import bg.softuni.mobilelele.service.OfferService;
import bg.softuni.mobilelele.service.impl.MobileleUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class OffersController {
    private final OfferService offerService;
    private final ModelMapper modelMapper;
    private final BrandService brandService;

    public OffersController(OfferService offerService, ModelMapper modelMapper, BrandService brandService) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
        this.brandService = brandService;
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

        //для визуализации данних в полях для редактирования оферти
        OfferDetailsView offerDetailsView = offerService.findById(id);
        OfferUpdateBindingModel offerModel = modelMapper.map(offerDetailsView, OfferUpdateBindingModel.class);
//добавляем падающее меню
        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());
        model.addAttribute("offerModel", offerModel);
        return "update";
    }

    @GetMapping("/offers/{id}/edit/errors")
    public String editOfferErrors(@PathVariable Long id, Model model) {

        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());
        return "update";
    }

    @PatchMapping("/offers/{id}/edit")
    public String editOffer(@PathVariable Long id,
                            @Valid OfferUpdateBindingModel offerModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            //flashAttribute -> ocelyavat pri POST-redirect->GET
             redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

            return "redirect:/offers/"+ id+ "/edit/errors";
        }

        OfferUpdateServiceModel serviceModel = modelMapper.map(offerModel, OfferUpdateServiceModel.class);
        offerModel.setId(id);

        offerService.updateOffer(serviceModel);
        return "redirect:/offers/" + id + "/details";
    }

    @GetMapping("/offers/add")
    public String getAddOfferPage(Model model) {
        // kind of "Security" to avoid browser address bar direct entry
        // TODO: This security will be removed soon :-)
//        if (!currentUser.isLoggedIn()) {
//            return "redirect:/users/login";
//        }
        if (!model.containsAttribute("offerAddBindModel")) {
            model.
                    addAttribute("offerAddBindModel", new OfferAddBindModel()).
                    addAttribute("brandsModels", brandService.getAllBrands());
        }
        return "offer-add";
    }

    @PostMapping("/offers/add")
    public String addOffer(@Valid OfferAddBindModel offerAddBindModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal MobileleUser user) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerAddBindModel", offerAddBindModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerAddBindModel", bindingResult)
                    .addFlashAttribute("brandsModels", brandService.getAllBrands());
            return "redirect:/offers/add";
        }
        OfferAddServiceModel savedOfferAddServiceModel = offerService.addOffer(offerAddBindModel, user.getUsername());
        return "redirect:/offers/" + savedOfferAddServiceModel.getId() + "/details";
    }


}

