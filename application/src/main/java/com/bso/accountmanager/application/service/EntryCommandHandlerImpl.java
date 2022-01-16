package com.bso.accountmanager.application.service;

import com.bso.accountmanager.domain.commands.entry.CreateEntryCommand;
import com.bso.accountmanager.application.factory.EntryFactory;
import com.bso.accountmanager.application.lock.AccountLockeable;
import com.bso.accountmanager.application.model.lock.LockManager;
import com.bso.accountmanager.domain.entity.account.Account;
import com.bso.accountmanager.domain.entity.account.Entry;
import com.bso.accountmanager.domain.exception.AccountNotFoundException;
import com.bso.accountmanager.domain.handlers.EntryCommandHandler;
import com.bso.accountmanager.domain.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
public class EntryCommandHandlerImpl implements EntryCommandHandler {

    private final AccountRepository accountRepository;
    private final LockManager lockManager;
    private final EntryFactory entryFactory;

    @Override
    public void handle(CreateEntryCommand command) {
        var branch = command.getBranch();
        var accountNumber = command.getAccountNumber();

        Optional<Account> accountOpt = accountRepository.findByBranchAndAccount(branch, accountNumber);
        AccountNotFoundException.throwsWhen(accountOpt.isEmpty(), branch, accountNumber);

        Account account = accountOpt.get();
        AccountLockeable accountLockeable = new AccountLockeable(account);
        lockManager.lockAndConsume(accountLockeable, () -> doProcess(command, account));
    }

    private void doProcess(CreateEntryCommand command, Account account) {
        Entry entry = entryFactory.create(command.getAmount(), command.getEntryType());
        account.processEntry(entry);
        accountRepository.save(account);
    }
}
