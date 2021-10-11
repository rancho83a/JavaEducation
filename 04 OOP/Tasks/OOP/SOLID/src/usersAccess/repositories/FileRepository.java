package usersAccess.repositories;

import usersAccess.resources.File;
import usersAccess.resources.Resource;

public class FileRepository  implements ResourceRepository{
    @Override
    public Resource fetchOne() {
        return new File();
    }
}
