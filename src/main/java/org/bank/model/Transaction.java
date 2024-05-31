package org.bank.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bank.repository.TransactionInterface;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Transaction implements TransactionInterface {
    private User from;
    private User to;
    private BigDecimal amount;

    private Transaction() {
    }


    public static class Builder {
        private final Transaction transaction;

        public Builder() {
            transaction = new Transaction();
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
