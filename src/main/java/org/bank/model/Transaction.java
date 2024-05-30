package org.bank.model;

import lombok.*;
import org.bank.repository.TransactionInterface;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Transaction implements TransactionInterface {
    private String transactionId;
    private User from;
    private User to;
    private BigDecimal amount;

    private Transaction() {
    }



    public static class Builder {
        private Transaction transaction;

        public Builder() {
            transaction = new Transaction();
        }

        public  Builder transactionId(String transactionId) {
            transaction.transactionId = transactionId;
            return this;
        }

        public Builder from(User from) {
            transaction.from = from;
            return this;
        }

        public Builder to(User to) {
            transaction.to = to;
            return this;
        }

        public Builder amount(double amount) {
            transaction.amount = BigDecimal.valueOf(amount);
            return this;
        }

        public Transaction build() {
            return transaction;
        }
    }
}
