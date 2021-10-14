package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.service.UserLoginServiceModel;
import bg.softuni.mobilelele.model.service.UserRegistrationServiceModel;
import org.springframework.stereotype.Service;



public interface UserService {
    boolean login (UserLoginServiceModel loginServiceModel);
    void logout();
    void initializeUsersAndRoles();

    void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);
}
