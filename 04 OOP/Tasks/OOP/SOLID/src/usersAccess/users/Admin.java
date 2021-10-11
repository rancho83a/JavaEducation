package usersAccess.users;

import usersAccess.resources.Resource;

public class Admin implements User {


    @Override
    public void read(Resource resource) {
        System.out.println("reading "+ resource.getClass());
    }
}
