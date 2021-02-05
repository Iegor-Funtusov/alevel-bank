package ua.com.alevel.alevelbank.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.alevelbank.persistence.entity.user.Personal;
import ua.com.alevel.alevelbank.persistence.repository.AbstractRepository;

@Repository
public interface PersonalRepository extends AbstractRepository<Personal> {
}
