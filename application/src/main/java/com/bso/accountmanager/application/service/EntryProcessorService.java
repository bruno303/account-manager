package com.bso.accountmanager.application.service;

import com.bso.accountmanager.application.dto.entry.EntryCreationDTO;
import com.bso.accountmanager.application.factory.EntryFactory;
import com.bso.accountmanager.application.lock.AccountLockeable;
import com.bso.accountmanager.application.model.lock.LockManager;
import com.bso.accountmanager.domain.entity.account.Account;
import com.bso.accountmanager.domain.entity.account.Entry;
import com.bso.accountmanager.domain.exception.AccountNotFoundException;
import com.bso.accountmanager.domain.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EntryProcessorService {

    private final AccountRepository accountRepository;
    private final LockManager lockManager;
    private final EntryFactory entryFactory;

    public void processNewEntry(EntryCreationDTO dto) {
        var branch = dto.getBranch();
        var accountNumber = dto.getAccount();

        Optional<Account> accountOpt = accountRepository.findByBranchAndAccount(branch, accountNumber);
        AccountNotFoundException.throwsWhen(accountOpt.isEmpty(), branch, accountNumber);

        Account account = accountOpt.get();
        AccountLockeable accountLockeable = new AccountLockeable(account);
        lockManager.lockAndConsume(accountLockeable, () -> doProcess(dto, account));
    }

    private void doProcess(EntryCreationDTO dto, Account account) {
        Entry entry = entryFactory.create(dto.getAmount(), dto.getEntryType());
        account.processEntry(entry);
        accountRepository.save(account);
    }
}
