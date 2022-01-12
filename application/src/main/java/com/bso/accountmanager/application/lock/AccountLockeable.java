package com.bso.accountmanager.application.lock;

import com.bso.accountmanager.application.model.lock.Lockeable;
import com.bso.accountmanager.domain.entity.account.Account;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountLockeable implements Lockeable {

    private final String lockKey;

    public AccountLockeable(Account account) {
        this.lockKey = String.format("account:%d:%d", account.getBranch(), account.getAccountNumber());
    }

    public AccountLockeable(Integer branch, Long account) {
        this.lockKey = String.format("account:%d:%d", branch, account);
    }

    @Override
    public String getLockKey() {
        return lockKey;
    }
}
