package org.bank.service.transaction;

import org.bank.model.User;
import org.bank.repository.TransactionInterface;

import java.math.BigDecimal;


public abstract class TransactionDecorator implements TransactionInterface {
    protected TransactionInterface decoratedTransaction;

    public TransactionDecorator(TransactionInterface decoratedTransaction) {
        this.decoratedTransaction = decoratedTransaction;
    }

    public static boolean isSufficientBudget(BigDecimal senderBudget, BigDecimal amount) {
        return senderBudget.compareTo(amount) >= 0;
    }

    @Override
    public BigDecimal getAmount() {
        return decoratedTransaction.getAmount();
    }

    @Override
    public User getFrom() {
        return decoratedTransaction.getFrom();
    }

    @Override
    public User getTo() {
        return decoratedTransaction.getTo();
    }

}