package ua.com.alevel.alevelbank.persistence.entity.balance;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.alevelbank.persistence.entity.AbstractEntity;
import ua.com.alevel.alevelbank.persistence.entity.user.Personal;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "balances")
public class Balance extends AbstractEntity {

    @ManyToOne
    private Personal personal;

    @Digits(integer = 7, fraction = 2)
    @Column(name = "balance_value", nullable = false)
    private BigDecimal balanceValue;

    public Balance() {
        super();
        this.balanceValue = new BigDecimal("00.00");
    }
}
