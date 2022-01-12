package com.bso.accountmanager.domain.exception;

public class AccountAlreadyExistsException extends DomainException{

    public AccountAlreadyExistsException(Integer branch, Long account) {
        super(String.format("Account already exists (%d/%d)", branch, account));
    }

    public static void throwsWhen(boolean condition, Integer branch, Long account) {
        if (condition) throw new AccountAlreadyExistsException(branch, account);
    }
}
