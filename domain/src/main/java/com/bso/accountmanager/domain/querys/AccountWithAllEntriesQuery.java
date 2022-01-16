package com.bso.accountmanager.domain.querys;

public class AccountWithAllEntriesQuery {
    private final Integer branch;
    private final Long accountNumber;

    public AccountWithAllEntriesQuery(Integer branch, Long accountNumber) {
        this.branch = branch;
        this.accountNumber = accountNumber;
    }

    public Integer getBranch() {
        return branch;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }
}
