package jsoprocessing.ex.model.Dto.viewDto.task4;

import com.google.gson.annotations.Expose;

import java.util.List;

public class UsersOutputDto {
    @Expose
    private int usersCount;
    @Expose
    private List<UserFirstLastNameAgeSoldProductsDto> users;

    public UsersOutputDto() {
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserFirstLastNameAgeSoldProductsDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserFirstLastNameAgeSoldProductsDto> users) {
        this.users = users;
    }
}
