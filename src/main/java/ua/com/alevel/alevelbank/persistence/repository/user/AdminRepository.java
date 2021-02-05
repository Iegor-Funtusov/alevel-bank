package ua.com.alevel.alevelbank.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.alevelbank.persistence.entity.user.Admin;
import ua.com.alevel.alevelbank.persistence.repository.AbstractRepository;

@Repository
public interface AdminRepository extends AbstractRepository<Admin> {
}
