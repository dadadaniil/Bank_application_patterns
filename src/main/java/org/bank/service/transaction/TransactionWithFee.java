package org.bank.service.transaction;

import org.bank.repository.TransactionInterface;

import java.math.BigDecimal;

public class TransactionWithFee extends TransactionDecorator {
    private final BigDecimal feePercentage;

    public TransactionWithFee(TransactionInterface decoratedTransaction, BigDecimal feePercentage) {
        super(decoratedTransaction);
        this.feePercentage = feePercentage.divide(BigDecimal.valueOf(100));
    }

    @Override
    public BigDecimal getAmount() {
        BigDecimal fee = decoratedTransaction.getAmount().multiply(feePercentage);
        return decoratedTransaction.getAmount().subtract(fee);
    }
}