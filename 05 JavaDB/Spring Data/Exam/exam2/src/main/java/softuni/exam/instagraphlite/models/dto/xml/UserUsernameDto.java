package softuni.exam.instagraphlite.models.dto.xml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class UserUsernameDto {
    @XmlElement(name="username")
    private String username;

    public UserUsernameDto() {
    }

    //@Size(min = 2, max = 18)
    @NotBlank
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
