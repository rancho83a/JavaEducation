package bg.softuni.mobilelele.model.entity;

import bg.softuni.mobilelele.model.entity.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private UserRoleEnum name;

    public UserRoleEnum getName() {
        return name;
    }

    public UserRoleEntity setName(UserRoleEnum name) {
        this.name = name;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserRoleEntity setId(Long id) {
        this.id = id;
        return this;
    }
}
