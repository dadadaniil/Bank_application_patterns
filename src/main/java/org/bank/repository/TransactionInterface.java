package org.bank.repository;

import org.bank.model.User;

import java.math.BigDecimal;

public interface TransactionInterface {
    BigDecimal getAmount();

    User getFrom();

    User getTo();

}
