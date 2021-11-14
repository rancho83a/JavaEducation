package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.view.BrandViewModel;
import bg.softuni.mobilelele.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }


    @GetMapping("/all")
    public String allBrands(Model model) {
        List<BrandViewModel> allBrands = this.brandService.getAllBrands();
        model.addAttribute("allBrands", this.brandService.getAllBrands());
        return "brands";

    }
}
