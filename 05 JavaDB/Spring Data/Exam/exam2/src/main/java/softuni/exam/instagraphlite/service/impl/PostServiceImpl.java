package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.xml.PostRootSeedDto;
import softuni.exam.instagraphlite.models.entity.Post;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;
import softuni.exam.instagraphlite.util.XMLParse;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PostServiceImpl implements PostService {
    private static final String POST_FILE_PATH = "src/main/resources/files/posts.xml";

    private final XMLParse xmlParse;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private PostRepository postRepository;
    private PictureService pictureService;

    private UserService userService;
    public PostServiceImpl(XMLParse xmlParse, ModelMapper modelMapper, ValidationUtil validationUtil,
                           PostRepository postRepository, PictureService pictureService, UserService userService) {
        this.xmlParse = xmlParse;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.postRepository = postRepository;
        this.pictureService = pictureService;

        this.userService = userService;
    }

    @Override
    public boolean areImported() {
        return this.postRepository.count()>0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(POST_FILE_PATH));    }

    @Override
    public String importPosts() throws IOException, JAXBException {

        StringBuilder sb = new StringBuilder();

        PostRootSeedDto postRootSeedDto = xmlParse
                .fromFile(POST_FILE_PATH, PostRootSeedDto.class);

        postRootSeedDto.getPosts()
                .stream()
                .filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && pictureService.isEntityExist(dto.getPicture().getPath())
                            && userService.isEntityExist(dto.getUser().getUsername());
                    sb.append(
                            isValid ? String.format("Successfully imported Post, made by %s",
                                    dto.getUser().getUsername()
                                   )
                                    : "Invalid Post"
                    ).append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    Post post = modelMapper.map(dto, Post.class);

                    post.setUser(this.userService.getUserByUsername(dto.getUser().getUsername()));
                    post.setPicture((this.pictureService.getPictureByPath(dto.getPicture().getPath())));

                    return post;
                })
                .forEach(postRepository::save);

        return sb.toString();
    }
}
