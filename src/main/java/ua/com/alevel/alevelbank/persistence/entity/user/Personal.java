package ua.com.alevel.alevelbank.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.alevelbank.persistence.type.RoleType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@DiscriminatorValue("PERSONAL")
public class Personal extends User {

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    public Personal() {
        super();
        setRoleType(RoleType.ROLE_PERSONAL);
    }
}
