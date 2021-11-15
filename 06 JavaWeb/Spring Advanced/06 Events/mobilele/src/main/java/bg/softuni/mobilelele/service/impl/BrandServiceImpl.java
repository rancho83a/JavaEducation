package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.BrandEntity;
import bg.softuni.mobilelele.model.view.BrandViewModel;
import bg.softuni.mobilelele.model.view.ModelViewModel;
import bg.softuni.mobilelele.repository.BrandRepository;
import bg.softuni.mobilelele.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initializeBrands() {
        if (brandRepository.count() == 0) {
            BrandEntity ford = new BrandEntity().setName("Ford");
            brandRepository.save(ford);
        }
    }

    @Override
    public List<BrandViewModel> getAllBrands() {


        return brandRepository.findAll().stream()
                .map(brandEntity -> {
                    BrandViewModel brandViewModel = modelMapper.map(brandEntity, BrandViewModel.class);

                    brandViewModel.setModels(
                            brandEntity.getModels()
                                    .stream()
                                    .map(modelEntity -> modelMapper.map(modelEntity, ModelViewModel.class))
                                    .collect(Collectors.toList())
                    );
                    return brandViewModel;
                })
                .collect(Collectors.toList());
    }
}
