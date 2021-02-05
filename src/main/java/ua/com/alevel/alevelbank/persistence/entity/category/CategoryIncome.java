package ua.com.alevel.alevelbank.persistence.entity.category;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.alevelbank.persistence.entity.AbstractEntity;
import ua.com.alevel.alevelbank.persistence.entity.balance.Transaction;
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
public class CategoryIncome extends AbstractEntity {

    @ManyToOne
    private Personal personal;

    @ManyToOne
    private Transaction transaction;

    @Digits(integer = 7, fraction = 2)
    @Column(name = "sum_value", nullable = false)
    private BigDecimal sumValue;

    public CategoryIncome() {
        super();
    }
}
