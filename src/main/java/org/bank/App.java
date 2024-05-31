package org.bank;

import lombok.extern.log4j.Log4j2;
import org.bank.model.Transaction;
import org.bank.model.User;
import org.bank.repository.TopUpStrategy;
import org.bank.repository.TransactionInterface;
import org.bank.service.topup.InCountryTopUpStrategy;
import org.bank.service.topup.OtherCountryTopUp;
import org.bank.service.transaction.TransactionService;
import org.bank.service.transaction.TransactionWithFee;

import java.math.BigDecimal;

/**
 * Hello world!
 */

@Log4j2
public class App {

    public static void main(String[] args) {
        User sender = new User.Builder().balance(1000d).build();
        User receiver = new User.Builder().balance(0).build();
        User foreignSender = new User.Builder().name("Joe Boden").balance(1000d).build();

        TransactionInterface baseTransaction = new Transaction.Builder().from(sender).to(receiver).amount(100d).build();

        TransactionWithFee fee = new TransactionWithFee(baseTransaction, BigDecimal.valueOf(99d));

        TransactionService.transferMoney(baseTransaction);
        System.out.println("Receiver's balance after transaction without fee: " + receiver.getBalance());

        TransactionService.transferMoney(fee);
        System.out.println("Receiver's balance after transaction with fee: " + receiver.getBalance());

        TransactionInterface insufficientBalanceTransaction = new Transaction
            .Builder()
            .from(foreignSender)
            .to(receiver)
            .amount(1500d)
            .build();

        TransactionService.transferMoney(insufficientBalanceTransaction);
        System.out.println();

        TopUpStrategy inCountryTopUpStrategy = new InCountryTopUpStrategy();
        TopUpStrategy otherCountryTopUpStrategy = new OtherCountryTopUp();

        otherCountryTopUpStrategy.topUp(foreignSender, 200d);
        System.out.println("Foreign sender's balance is:"+ foreignSender.getBalance());

        inCountryTopUpStrategy.topUp(foreignSender, 350d);
        System.out.println("Foreign sender's balance is:"+ foreignSender.getBalance());
        TransactionService.transferMoney(insufficientBalanceTransaction);
        System.out.println("Receiver's balance after transaction with sufficient funds: " + receiver.getBalance());
    }
}
