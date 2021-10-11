package usersAccess;

import usersAccess.repositories.ResourceRepository;
import usersAccess.resources.Resource;
import usersAccess.users.User;

public class UserService {


    private ResourceRepository resourceRepository;

    public UserService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public void fetchOneFor (User user){
        Resource resource =this.resourceRepository.fetchOne();
        user.read(resource);
    }


}
