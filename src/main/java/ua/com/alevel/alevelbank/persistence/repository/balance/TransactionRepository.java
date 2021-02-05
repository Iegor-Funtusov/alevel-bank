package ua.com.alevel.alevelbank.persistence.repository.balance;

import org.springframework.stereotype.Repository;
import ua.com.alevel.alevelbank.persistence.entity.balance.Transaction;
import ua.com.alevel.alevelbank.persistence.repository.AbstractRepository;

@Repository
public interface TransactionRepository extends AbstractRepository<Transaction> {
}
