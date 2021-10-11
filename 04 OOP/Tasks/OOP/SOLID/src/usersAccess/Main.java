package usersAccess;

import usersAccess.repositories.TextFileRepository;
import usersAccess.resources.File;
import usersAccess.resources.Music;
import usersAccess.resources.Picture;
import usersAccess.resources.Resource;
import usersAccess.users.Admin;
import usersAccess.users.User;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserService(new TextFileRepository());
        User admin = new Admin();
        userService.fetchOneFor(admin);
    }
}
