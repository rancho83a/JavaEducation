package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.UserSeedDto;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.models.entity.Post;
import softuni.exam.instagraphlite.models.entity.User;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final String USER_FILE_PATH = "src/main/resources/files/users.json";

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserRepository userRepository;

    private final PictureService pictureService;

    public UserServiceImpl(Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, UserRepository userRepository, PictureService pictureService) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userRepository = userRepository;

        this.pictureService = pictureService;
    }

    @Override
    public boolean areImported() {
        return this.userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(USER_FILE_PATH));
    }

    @Override
    public String importUsers() throws IOException {

        StringBuilder sb = new StringBuilder();

        UserSeedDto[] pictureSeedDtos = gson.fromJson(readFromFileContent(), UserSeedDto[].class);
        Arrays.stream(pictureSeedDtos)
                .filter(c -> {

                    boolean isValid = validationUtil.isValid(c)
                            && !isEntityExist(c.getUsername()) // UNIQUE condition
                            && pictureService.isEntityExist(c.getProfilePicture()); //exist picture with path

                    sb.append(
                            isValid ? String.format("Successfully imported User: %s", c.getUsername())
                                    : "Invalid User"
                    ).append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    User us = modelMapper.map(dto, User.class);
                    Picture pic = this.pictureService.getPictureByPath(dto.getProfilePicture());
                    us.setProfilePicture(pic);
                    return us;
                })
                .forEach(userRepository::save);

        return sb.toString();
    }

    @Override
    public boolean isEntityExist(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public String exportUsersWithTheirPosts() {
        StringBuilder sb = new StringBuilder();


        List<User> users = this.userRepository.findAllWithPostsOrderedByCountPosts();

        users
                .forEach(u -> {
                    sb.append(
                            "User: " + u.getUsername() + "\n" +
                                    "Post count: " + u.getPosts().size() + "\n" +
                                    "==Post Details:" + "\n");
                    u.getPosts()
                            .stream().sorted(Comparator.comparingDouble(p -> p.getPicture().getSize()))
                            .forEach(p -> {

                                sb.append(
                                        "----Caption: " + p.getCaption() + "\n" +
                                                String.format("----Picture Size: %.2f", p.getPicture().getSize()) + "\n"
                                );
                            });
                });
        return sb.toString();
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findFirstByUsername(username);
    }


}
