package org.bank.service.topup;

import lombok.extern.log4j.Log4j2;
import org.bank.model.Transaction;
import org.bank.model.User;
import org.bank.repository.TopUpStrategy;
import org.bank.repository.TransactionInterface;
import org.bank.service.transaction.TransactionService;
import org.bank.service.transaction.TransactionWithFee;

import java.math.BigDecimal;

import static org.bank.service.transaction.TransactionDecorator.isSufficientBudget;

@Log4j2
public class OtherCountryTopUp implements TopUpStrategy {
    private final static BigDecimal COMMISSION_PERCENT_RATE = BigDecimal.valueOf(0.99d);

    @Override
    public void topUp(User receiver, double amount) {
        BigDecimal amountBigDecimal = BigDecimal.valueOf(amount);
        receiver.increaseBalance(extractCommission(amountBigDecimal));
    }

    @Override
    public boolean fromOtherAccount(User sender, User receiver, double amount) {
        BigDecimal amountBigDecimal = BigDecimal.valueOf(amount);
        TransactionInterface transaction = new Transaction.Builder()
                .from(sender)
                .to(receiver)
                .amount(amount)
                .build();

        TransactionInterface transactionWithFee = new TransactionWithFee(transaction,
                amountBigDecimal.multiply(COMMISSION_PERCENT_RATE));

        return TransactionService.transferMoney(transactionWithFee);
    }


    private BigDecimal extractCommission(BigDecimal amount) {
        return amount.multiply(COMMISSION_PERCENT_RATE);
    }

}
