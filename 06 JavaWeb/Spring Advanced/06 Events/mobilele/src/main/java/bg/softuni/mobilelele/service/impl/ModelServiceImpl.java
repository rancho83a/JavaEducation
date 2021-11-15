package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.BrandEntity;
import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.model.entity.enums.CategoryEnum;
import bg.softuni.mobilelele.repository.BrandRepository;
import bg.softuni.mobilelele.repository.ModelRepository;
import bg.softuni.mobilelele.service.ModelService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ModelServiceImpl implements ModelService {

private final ModelRepository modelRepository;
private final BrandRepository brandRepository;


    public ModelServiceImpl(ModelRepository modelRepository, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }


    @Override
    public void initializeModels() {

        BrandEntity ford= brandRepository.findByName("Ford");

        if (modelRepository.count() == 0) {

                ModelEntity fiesta = new ModelEntity();
                fiesta.setName("Fiesta")
                        .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg/280px-2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg")
                        .setStartYear(1976)
                        .setCategory(CategoryEnum.CAR)
                        .setBrand(ford);

                ModelEntity escort = new ModelEntity();
                escort.setName("Escort")
                        .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Ford_Escort_RS2000_MkI.jpg/280px-Ford_Escort_RS2000_MkI.jpg")
                        .setStartYear(1968)
                        .setEndYear(2002)
                        .setCategory(CategoryEnum.CAR)
                                .setBrand(ford);

                modelRepository.saveAll(List.of(fiesta, escort));
            }
    }
}
