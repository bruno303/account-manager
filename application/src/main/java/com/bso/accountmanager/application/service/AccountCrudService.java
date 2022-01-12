package com.bso.accountmanager.application.service;

import com.bso.accountmanager.application.dto.contract.AccountCreationDTO;
import com.bso.accountmanager.application.factory.AccountFactory;
import com.bso.accountmanager.application.lock.AccountLockeable;
import com.bso.accountmanager.application.model.lock.LockManager;
import com.bso.accountmanager.domain.entity.account.Account;
import com.bso.accountmanager.domain.events.EventRaiser;
import com.bso.accountmanager.domain.exception.AccountAlreadyExistsException;
import com.bso.accountmanager.domain.exception.AccountNotFoundException;
import com.bso.accountmanager.domain.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
public class AccountCrudService {

    private final AccountFactory accountFactory;
    private final AccountRepository accountRepository;
//    private final EventRaiser eventRaiser;
    private final LockManager lockManager;

    public Account createContract(AccountCreationDTO dto) {
        var contractLockeable = new AccountLockeable(dto.getBranch(), dto.getAccount());
        return lockManager.lockAndProcess(contractLockeable, () -> doCreate(dto));
    }

    public Account doCreate(AccountCreationDTO dto) {
        Optional<Account> accountOpt = accountRepository.findByBranchAndAccount(dto.getBranch(), dto.getAccount());
        AccountAlreadyExistsException.throwsWhen(accountOpt.isPresent(), dto.getBranch(), dto.getAccount());

        Account account = accountFactory.create(dto.getBranch(), dto.getAccount(), dto.getBalance());
        account = accountRepository.saveAndFlush(account);
//        eventRaiser.raise(new AccountCreatedEvent(account));
        return account;
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findByBranchAndAccountWithAllEntries(Integer branch, Long accountNumber) {
        Optional<Account> accountOpt = accountRepository.findByBranchAndAccountWithAllEntries(branch, accountNumber);
        AccountNotFoundException.throwsWhen(accountOpt.isEmpty(), branch, accountNumber);

        return accountOpt.get();
    }
}
