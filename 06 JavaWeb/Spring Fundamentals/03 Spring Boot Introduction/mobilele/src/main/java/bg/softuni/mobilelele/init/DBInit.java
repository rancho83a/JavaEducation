package bg.softuni.mobilelele.init;

import bg.softuni.mobilelele.model.entity.BrandEntity;
import bg.softuni.mobilelele.model.entity.CategoryEnum;
import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.model.entity.TestEntityWithUUID;
import bg.softuni.mobilelele.repository.BrandRepository;
import bg.softuni.mobilelele.repository.TestWithUUIDRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DBInit implements CommandLineRunner {

private  final TestWithUUIDRepository testWithUUIDRepository;

private final BrandRepository brandRepository;

    public DBInit(TestWithUUIDRepository testWithUUIDRepository, BrandRepository brandRepository) {
        this.testWithUUIDRepository = testWithUUIDRepository;
        this.brandRepository = brandRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        //testWithUUIDRepository.save(new TestEntityWithUUID().setName("test, Hello!"));

        if(brandRepository.count()==0) {
            BrandEntity ford = new BrandEntity().setName("Ford");

            ModelEntity fiesta =new ModelEntity();
            fiesta.setName("Fiesta")
                    .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg/280px-2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg")
                    .setStartYear(1976)
                    .setCategory(CategoryEnum.CAR);

            ModelEntity escort =new ModelEntity();
            escort.setName("Escort")
                    .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Ford_Escort_RS2000_MkI.jpg/280px-Ford_Escort_RS2000_MkI.jpg")
                    .setStartYear(1968)
                    .setEndYear(2002)
                    .setCategory(CategoryEnum.CAR);
        ford.setModels(List.of(fiesta,escort));
        fiesta.setBrand(ford);
        escort.setBrand(ford);
        brandRepository.save(ford);
        }


    }
}
