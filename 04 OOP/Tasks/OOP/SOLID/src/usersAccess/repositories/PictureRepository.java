package usersAccess.repositories;

import usersAccess.resources.Picture;
import usersAccess.resources.Resource;

public class PictureRepository implements ResourceRepository {
    @Override
    public Resource fetchOne() {
        return new Picture();
    }
}
