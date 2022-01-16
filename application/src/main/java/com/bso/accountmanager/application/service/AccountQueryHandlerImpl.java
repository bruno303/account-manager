package com.bso.accountmanager.application.service;

import com.bso.accountmanager.domain.handlers.AccountQueryHandler;
import com.bso.accountmanager.domain.querys.AccountWithAllEntriesQuery;
import com.bso.accountmanager.domain.querys.AllAccountsQuery;
import com.bso.accountmanager.domain.entity.account.Account;
import com.bso.accountmanager.domain.exception.AccountNotFoundException;
import com.bso.accountmanager.domain.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
public class AccountQueryHandlerImpl implements AccountQueryHandler {

    private final AccountRepository accountRepository;

    @Override
    public Account handle(AccountWithAllEntriesQuery query) {
        Optional<Account> accountOpt = accountRepository.findByBranchAndAccountWithAllEntries(query.getBranch(), query.getAccountNumber());
        AccountNotFoundException.throwsWhen(accountOpt.isEmpty(), query.getBranch(), query.getAccountNumber());

        return accountOpt.get();
    }

    @Override
    public List<Account> handle(AllAccountsQuery query) {
        return accountRepository.findAll();
    }
}
