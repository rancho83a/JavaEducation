package ex.xmlprocessing.Dto.query2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserViewRootDto {
@XmlElement(name="user")
    private List<UserFirstLastNameProductsDto> users;

    public UserViewRootDto() {
    }

    public List<UserFirstLastNameProductsDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserFirstLastNameProductsDto> users) {
        this.users = users;
    }
}
