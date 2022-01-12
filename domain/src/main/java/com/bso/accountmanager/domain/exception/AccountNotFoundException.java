package com.bso.accountmanager.domain.exception;

public class AccountNotFoundException extends DomainException{

    public AccountNotFoundException(Integer branch, Long account) {
        super(String.format("Account (%d/%d) not found", branch, account));
    }

    public static void throwsWhen(boolean condition, Integer branch, Long account) {
        if (condition) throw new AccountNotFoundException(branch, account);
    }
}
