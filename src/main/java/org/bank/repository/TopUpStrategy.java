package org.bank.repository;

import org.bank.model.User;

public interface TopUpStrategy {
    void topUp(User receiver, double amount);

    boolean fromOtherAccount(User sender, User receiver, double amount);

}
