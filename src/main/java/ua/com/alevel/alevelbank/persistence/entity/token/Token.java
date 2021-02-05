package ua.com.alevel.alevelbank.persistence.entity.token;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.alevelbank.persistence.entity.AbstractEntity;
import ua.com.alevel.alevelbank.persistence.entity.user.User;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tokens")
public class Token extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(nullable = false, unique = true)
    private String tokenUUID;

    public Token() {
        super();
    }
}
