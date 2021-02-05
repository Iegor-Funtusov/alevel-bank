package ua.com.alevel.alevelbank.service.balance.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.alevelbank.persistence.entity.balance.Balance;
import ua.com.alevel.alevelbank.persistence.entity.balance.Transaction;
import ua.com.alevel.alevelbank.persistence.entity.user.Personal;
import ua.com.alevel.alevelbank.persistence.repository.balance.BalanceRepository;
import ua.com.alevel.alevelbank.persistence.repository.balance.TransactionRepository;
import ua.com.alevel.alevelbank.persistence.repository.user.PersonalRepository;
import ua.com.alevel.alevelbank.service.balance.BalanceService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;
    private final PersonalRepository personalRepository;
    private final TransactionRepository transactionRepository;

    public BalanceServiceImpl(BalanceRepository balanceRepository, PersonalRepository personalRepository, TransactionRepository transactionRepository) {
        this.balanceRepository = balanceRepository;
        this.personalRepository = personalRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Balance> findByPersonal(Personal personal) {
        List<Balance> balances =  balanceRepository.findByPersonal(personal);
        if (balances == null) {
            return Collections.emptyList();
        }
        return balances;
    }

    @Override
    public void transfer(String sum, Personal personal, Integer to) {
        List<Balance> balances = balanceRepository.findByPersonal(personal);
        Balance from = balances.get(0);
        Personal personalTo = personalRepository.findById(to).get();
        List<Balance> balancesTo = balanceRepository.findByPersonal(personalTo);
        Balance balanceTo = balancesTo.get(0);

        BigDecimal result = new BigDecimal(sum);
        BigDecimal resultFrom = from.getBalanceValue().subtract(result);
        BigDecimal resultTo = balanceTo.getBalanceValue().add(result);

        from.setBalanceValue(resultFrom);
        from = balanceRepository.save(from);

        balanceTo.setBalanceValue(resultTo);
        balanceTo = balanceRepository.save(balanceTo);

        Transaction transaction = new Transaction();
        transaction.setBalanceFrom(from);
        transaction.setBalanceTo(balanceTo);
        transaction.setPersonalFrom(personal);
        transaction.setPersonalTo(personalTo);
        transaction.setSumValue(result);

        transactionRepository.save(transaction);
    }
}
