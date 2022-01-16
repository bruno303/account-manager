package com.bso.accountmanager.application.service;

import com.bso.accountmanager.domain.commands.account.CreateAccountCommand;
import com.bso.accountmanager.application.factory.AccountFactory;
import com.bso.accountmanager.application.lock.AccountLockeable;
import com.bso.accountmanager.application.model.lock.LockManager;
import com.bso.accountmanager.domain.entity.account.Account;
import com.bso.accountmanager.domain.exception.AccountAlreadyExistsException;
import com.bso.accountmanager.domain.handlers.AccountCommandHandler;
import com.bso.accountmanager.domain.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
public class AccountCommandHandlerImpl implements AccountCommandHandler {

    private final AccountFactory accountFactory;
    private final AccountRepository accountRepository;
    private final LockManager lockManager;

    @Override
    public void handle(CreateAccountCommand command) {
        var contractLockeable = new AccountLockeable(command.getBranch(), command.getAccount());
        lockManager.lockAndProcess(contractLockeable, () -> doCreate(command));
    }

    private Account doCreate(CreateAccountCommand command) {
        Optional<Account> accountOpt = accountRepository.findByBranchAndAccount(command.getBranch(), command.getAccount());
        AccountAlreadyExistsException.throwsWhen(accountOpt.isPresent(), command.getBranch(), command.getAccount());

        Account account = accountFactory.create(command.getBranch(), command.getAccount(), command.getBalance());
        account = accountRepository.saveAndFlush(account);
        return account;
    }
}
