package ua.com.alevel.alevelbank.persistence.repository.balance;

import org.springframework.stereotype.Repository;
import ua.com.alevel.alevelbank.persistence.entity.balance.Balance;
import ua.com.alevel.alevelbank.persistence.entity.user.Personal;
import ua.com.alevel.alevelbank.persistence.repository.AbstractRepository;

import java.util.List;

@Repository
public interface BalanceRepository extends AbstractRepository<Balance> {

    List<Balance> findByPersonal(Personal personal);
}
