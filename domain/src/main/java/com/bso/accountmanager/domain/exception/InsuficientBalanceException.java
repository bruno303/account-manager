package com.bso.accountmanager.domain.exception;

import java.math.BigDecimal;

public class InsuficientBalanceException extends DomainException{

    public InsuficientBalanceException(Integer branch, Long account, BigDecimal balance, BigDecimal amount) {
        super(String.format("Insuficient balance for account (%d/%d). Balance: %f. Amount %f", branch, account, balance, amount));
    }

    public static void throwsWhen(boolean condition, Integer branch, Long account, BigDecimal balance, BigDecimal amount) {
        if (condition) throw new InsuficientBalanceException(branch, account, balance, amount);
    }
}
