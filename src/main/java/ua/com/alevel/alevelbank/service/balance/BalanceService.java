package ua.com.alevel.alevelbank.service.balance;

import ua.com.alevel.alevelbank.persistence.entity.balance.Balance;
import ua.com.alevel.alevelbank.persistence.entity.user.Personal;

import java.util.List;

public interface BalanceService {

    List<Balance> findByPersonal(Personal personal);
    void transfer(String sum, Personal personal, Integer to);
}
