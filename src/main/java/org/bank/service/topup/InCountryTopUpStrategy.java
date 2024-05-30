package org.bank.service.topup;

import lombok.extern.log4j.Log4j2;
import org.bank.model.Transaction;
import org.bank.model.User;
import org.bank.repository.TopUpStrategy;
import org.bank.repository.TransactionInterface;
import org.bank.service.transaction.TransactionService;

import java.math.BigDecimal;

@Log4j2

public class InCountryTopUpStrategy implements TopUpStrategy {

    @Override
    public void topUp(User receiver, double amount) {
        BigDecimal amountBigDecimal = BigDecimal.valueOf(amount);
        receiver.increaseBalance(receiver.getBalance().add(amountBigDecimal));
    }

    @Override
    public boolean fromOtherAccount(User sender, User receiver, double amount) {
        BigDecimal amountBigDecimal = BigDecimal.valueOf(amount);
        TransactionInterface transaction = new Transaction.Builder()
                .from(sender)
                .to(receiver)
                .amount(amount)
                .build();


        return TransactionService.transferMoney(transaction);
    }
}
