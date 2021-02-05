package ua.com.alevel.alevelbank.persistence.entity.balance;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.alevelbank.persistence.entity.AbstractEntity;
import ua.com.alevel.alevelbank.persistence.entity.user.Personal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction extends AbstractEntity {

    @Digits(integer = 7, fraction = 2)
    @Column(name = "sum_value", nullable = false)
    private BigDecimal sumValue;

    @ManyToOne
    Personal personalFrom;

    @ManyToOne
    Personal personalTo;

    @ManyToOne
    Balance balanceFrom;

    @ManyToOne
    Balance balanceTo;

    public Transaction() {
        super();
    }
}
