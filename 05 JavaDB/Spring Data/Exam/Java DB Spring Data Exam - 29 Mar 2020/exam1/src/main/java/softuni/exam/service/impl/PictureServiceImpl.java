package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Dto.PictureSeedDto;
import softuni.exam.models.entity.Picture;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class PictureServiceImpl implements PictureService {

    private static final String PICTURE_FILE_PATH = "src/main/resources/files/json/pictures.json";
    private final PictureRepository pictureRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CarService carService;

    public PictureServiceImpl(PictureRepository pictureRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, CarService carService) {

        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.carService = carService;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count()>0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {

        return Files.readString(Path.of(PICTURE_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb=new StringBuilder();

        PictureSeedDto[] pictureSeedDtos = gson.fromJson(readPicturesFromFile(), PictureSeedDto[].class);
        Arrays.stream(pictureSeedDtos)
                .filter(c->{

                    boolean isValid = validationUtil.isValid(c);

                    sb.append(
                            isValid ? String.format("Successfully import picture - %s", c.getName())
                                    : "Invalid picture"
                    ).append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    Picture pic = modelMapper.map(dto, Picture.class);
                    pic.setCar(this.carService.getCarById(dto.getCar()));

                    return pic;
                })
                .forEach(pictureRepository::save);

        return sb.toString();
    }
}
