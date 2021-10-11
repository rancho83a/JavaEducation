package softuni.exam.instagraphlite.service;

import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.models.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    boolean areImported();
    String readFromFileContent() throws IOException;
    String importUsers() throws IOException;

    boolean isEntityExist(String username);

    String exportUsersWithTheirPosts();
    User getUserByUsername(String username);

}
