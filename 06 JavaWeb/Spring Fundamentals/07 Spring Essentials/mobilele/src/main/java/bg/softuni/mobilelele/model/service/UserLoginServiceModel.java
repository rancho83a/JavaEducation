package bg.softuni.mobilelele.model.service;

public class UserLoginServiceModel {
    private String username;
    private String rowPassword;

    public String getUsername() {
        return username;
    }

    public UserLoginServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getRowPassword() {
        return rowPassword;
    }

    public UserLoginServiceModel setRowPassword(String rowPassword) {
        this.rowPassword = rowPassword;
        return this;
    }
}
