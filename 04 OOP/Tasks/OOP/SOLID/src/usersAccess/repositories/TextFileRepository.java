package usersAccess.repositories;

import usersAccess.resources.Resource;
import usersAccess.resources.TextFile;

public class TextFileRepository implements ResourceRepository{

    @Override
    public Resource fetchOne() {
        return new TextFile();
    }
}
