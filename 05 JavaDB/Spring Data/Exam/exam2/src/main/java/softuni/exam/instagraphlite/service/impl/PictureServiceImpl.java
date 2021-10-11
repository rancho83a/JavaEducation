package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.PictureSeedDto;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    private static final String PICTURE_FILE_PATH = "src/main/resources/files/pictures.json";

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final PictureRepository pictureRepository;

    public PictureServiceImpl(Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, PictureRepository pictureRepository) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(PICTURE_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();

        PictureSeedDto[] pictureSeedDtos = gson.fromJson(readFromFileContent(), PictureSeedDto[].class);
        Arrays.stream(pictureSeedDtos)
                .filter(c -> {

                    boolean isValid = validationUtil.isValid(c) && !isEntityExist(c.getPath());

                    sb.append(
                            isValid ? String.format("Successfully imported Picture, with size %.2f", c.getSize())
                                    : "Invalid Picture"
                    ).append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    Picture pic = modelMapper.map(dto, Picture.class);

                    return pic;
                })
                .forEach(pictureRepository::save);

        return sb.toString();
    }

    @Override
    public boolean isEntityExist(String path) {
        return this.pictureRepository.existsByPath(path);
    }

    @Override
    public String exportPictures() {

        StringBuilder sb =new StringBuilder();

        List<Picture> pics = this.pictureRepository.findAllBySizeGreaterThanOrderBySizeAsc(30000D);
        pics.forEach(p->{

            sb.append(
                    p.getSize()+" - "+p.getPath()+"\n"

            );
        });


        return sb.toString();
    }

    @Override
    public Picture getPictureByPath(String path) {
        return this.pictureRepository.findFirstByPath(path).orElse(null);
    }
}
