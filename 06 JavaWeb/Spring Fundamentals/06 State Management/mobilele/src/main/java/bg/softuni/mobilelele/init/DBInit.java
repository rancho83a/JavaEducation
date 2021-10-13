package bg.softuni.mobilelele.init;

import bg.softuni.mobilelele.model.entity.BrandEntity;
import bg.softuni.mobilelele.model.entity.enums.CategoryEnum;
import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.service.BrandService;
import bg.softuni.mobilelele.service.ModelService;
import bg.softuni.mobilelele.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DBInit implements CommandLineRunner {

    private final UserService userService;
    private final BrandService brandService;
    private final ModelService modelService;

    public DBInit(UserService userService, BrandService brandService, ModelService modelService) {
        this.userService = userService;
        this.brandService = brandService;
        this.modelService = modelService;
    }

    @Override
    public void run(String... args) throws Exception {

        brandService.initializeBrands();
        modelService.initializeModels();
        userService.initializeUsersAndRoles();
    }
}