package org.bank.service.transaction;

import lombok.extern.log4j.Log4j2;
import org.bank.model.User;
import org.bank.repository.TransactionInterface;

import java.math.BigDecimal;

@Log4j2

public class TransactionService {
    public static boolean transferMoney(TransactionInterface transaction) {
        User sender = transaction.getFrom();
        User receiver = transaction.getTo();
        BigDecimal amount = transaction.getAmount();

        if (TransactionDecorator.isSufficientBudget(sender.getBalance(), amount)) {
            sender.decreaseBalance(amount);
            receiver.increaseBalance(amount);
            return true;
        } else {
            log.info("User with email {} has insufficient funds for transfer, operation cancelled", sender.getEmail());
            return false;
        }
    }
}