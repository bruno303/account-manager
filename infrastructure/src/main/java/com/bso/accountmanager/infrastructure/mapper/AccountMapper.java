package com.bso.accountmanager.infrastructure.mapper;

import com.bso.accountmanager.infrastructure.entities.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountMapper {

    private final EntryMapper entryMapper;

    public Account toPersistenceAccount(com.bso.accountmanager.domain.entity.account.Account account) {
        var persistenceAccount = new Account();
        persistenceAccount.setBranch(account.getBranch());
        persistenceAccount.setAccountNumber(account.getAccountNumber());
        persistenceAccount.setBalance(account.getBalance());
        persistenceAccount.setId(account.getId());
        persistenceAccount.setEntries(entryMapper.toPersistenceEntries(account.getEntries()));
        return persistenceAccount;
    }

    public com.bso.accountmanager.domain.entity.account.Account toDomainAccount(Account account) {
        var entries = entryMapper.toDomainEntries(account.getEntries());

        return new com.bso.accountmanager.domain.entity.account.Account(account.getId(),
                account.getBranch(), account.getAccountNumber(), entries, account.getBalance());
    }
}
