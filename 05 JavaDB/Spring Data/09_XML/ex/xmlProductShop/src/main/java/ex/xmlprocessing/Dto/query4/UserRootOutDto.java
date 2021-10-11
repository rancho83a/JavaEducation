package ex.xmlprocessing.Dto.query4;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserRootOutDto {
    @XmlAttribute
    private String count;
    @XmlElement(name="user")
    private List<UserFirstLastNameAgeDto> users;

    public UserRootOutDto() {
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<UserFirstLastNameAgeDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserFirstLastNameAgeDto> users) {
        this.users = users;
    }
}
