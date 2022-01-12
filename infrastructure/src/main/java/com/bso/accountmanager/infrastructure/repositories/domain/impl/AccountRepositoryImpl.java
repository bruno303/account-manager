package com.bso.accountmanager.infrastructure.repositories.domain.impl;

import com.bso.accountmanager.domain.entity.account.Account;
import com.bso.accountmanager.domain.repositories.AccountRepository;
import com.bso.accountmanager.infrastructure.entities.Entry;
import com.bso.accountmanager.infrastructure.mapper.AccountMapper;
import com.bso.accountmanager.infrastructure.repositories.PersistenceAccountRepository;
import com.bso.accountmanager.infrastructure.repositories.PersistenceEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final PersistenceAccountRepository accountRepository;
    private final PersistenceEntryRepository entryRepository;
    private final AccountMapper accountMapper;

    @Override
    public Optional<Account> findById(UUID id) {
        var accountOpt = accountRepository.findById(id);
        return accountOpt.map(accountMapper::toDomainAccount);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll()
            .stream()
            .map(accountMapper::toDomainAccount)
            .collect(Collectors.toList());
    }

    @Override
    public Account save(Account entity) {
        var persistenceAccount = accountMapper.toPersistenceAccount(entity);
        var accountSaved = accountRepository.save(persistenceAccount);

        saveEntries(accountSaved, persistenceAccount.getEntries());

        return accountMapper.toDomainAccount(accountSaved);
    }

    private void saveEntries(com.bso.accountmanager.infrastructure.entities.Account accountSaved, List<Entry> persistenceEntries) {
        if (persistenceEntries == null) {
            return;
        }

        accountSaved.setEntries(persistenceEntries);

        accountSaved.getEntries()
                .stream()
                .peek(e -> e.setAccountId(accountSaved.getId()))
                .forEach(entryRepository::saveAndFlush);
    }

    @Override
    public void delete(Account entity) {
        var persistenceAccount = accountMapper.toPersistenceAccount(entity);
        accountRepository.delete(persistenceAccount);
    }

    @Override
    public Account saveAndFlush(Account entity) {
        var persistenceAccount = accountMapper.toPersistenceAccount(entity);
        var saved = accountRepository.saveAndFlush(persistenceAccount);
        saveEntries(saved, persistenceAccount.getEntries());
        return accountMapper.toDomainAccount(saved);
    }

    @Override
    public Optional<Account> findByBranchAndAccount(Integer branch, Long account) {
        return accountRepository.findByBranchAndAccountNumber(branch, account).map(accountMapper::toDomainAccount);
    }

    @Override
    public Optional<Account> findByBranchAndAccountWithAllEntries(Integer branch, Long accountNumber) {
        var accountOpt = accountRepository.findByBranchAndAccountNumber(branch, accountNumber);
        if (accountOpt.isEmpty()) {
            return Optional.empty();
        }

        var account = accountOpt.get();

        List<Entry> entries = entryRepository.findByAccountId(account.getId());
        account.setEntries(entries);

        return Optional.of(accountMapper.toDomainAccount(account));
    }
}
