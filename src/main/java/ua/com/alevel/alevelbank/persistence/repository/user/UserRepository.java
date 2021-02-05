package ua.com.alevel.alevelbank.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.alevelbank.persistence.entity.user.User;
import ua.com.alevel.alevelbank.persistence.repository.AbstractRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends AbstractRepository<User> {

    Optional<User> findByEmail(String email);
}
