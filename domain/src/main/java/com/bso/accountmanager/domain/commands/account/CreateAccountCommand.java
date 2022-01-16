package com.bso.accountmanager.domain.commands.account;

import java.io.Serializable;
import java.math.BigDecimal;

public class CreateAccountCommand implements Serializable {
    private final Integer branch;
    private final Long account;
    private final BigDecimal balance;

    public CreateAccountCommand(Integer branch, Long account, BigDecimal balance) {
        this.branch = branch;
        this.account = account;
        this.balance = balance;
    }

    public Integer getBranch() {
        return branch;
    }

    public Long getAccount() {
        return account;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
