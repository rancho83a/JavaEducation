package project.nextleveltechnology.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginDto {
    private String username;
    private String password;

    public UserLoginDto() {
    }

    @NotBlank
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank
    @Size(min=3)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
