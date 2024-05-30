package org.bank.model;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    private BigDecimal balance;
    private String name;
    private String email;

    public void increaseBalance(BigDecimal sum){
        setBalance(balance.add(sum));
    }

    public void decreaseBalance(BigDecimal sum){
        setBalance(balance.subtract(sum));
    }


    private User() {
    }

    public static class Builder {
        private final User user;

        public Builder() {
            user = new User();
        }

        public Builder balance(double balance) {
            user.balance = BigDecimal.valueOf(balance);
            return this;
        }

        public Builder name(String name) {
            user.name = name;
            return this;
        }

        public Builder email(String email) {
            user.email = email;
            return this;
        }

        public User build() {
            return user;
        }
    }
}
