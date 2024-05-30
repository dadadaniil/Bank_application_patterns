package org.bank.service.transaction;


import org.bank.repository.TransactionInterface;

import java.math.BigDecimal;

public class TransactionWithFee extends TransactionDecorator {
    private BigDecimal fee;

    public TransactionWithFee(TransactionInterface decoratedTransaction, BigDecimal fee) {
        super(decoratedTransaction);
        this.fee = fee;
    }

    @Override
    public BigDecimal getAmount() {
        return decoratedTransaction.getAmount().add(fee);
    }
}