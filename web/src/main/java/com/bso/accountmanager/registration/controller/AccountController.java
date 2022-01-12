package com.bso.accountmanager.registration.controller;

import com.bso.accountmanager.application.dto.contract.AccountCreationDTO;
import com.bso.accountmanager.application.dto.entry.EntryCreationDTO;
import com.bso.accountmanager.application.service.AccountCrudService;
import com.bso.accountmanager.application.service.EntryProcessorService;
import com.bso.accountmanager.domain.entity.account.Account;
import com.bso.accountmanager.domain.entity.account.Entry;
import com.bso.accountmanager.registration.config.AccountManagerController;
import com.bso.accountmanager.registration.dto.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AccountManagerController
@RequiredArgsConstructor
public class AccountController {

    private final AccountCrudService accountCrudService;
    private final EntryProcessorService entryProcessorService;

    @GetMapping("v1/account")
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        List<Account> accounts = accountCrudService.findAll();
        return new ResponseEntity<>(toResponse(accounts), HttpStatus.OK);
    }

    @PostMapping("v1/account")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountCreationDTO dto) {
        Account account = accountCrudService.createContract(dto);
        return new ResponseEntity<>(toResponse(account), HttpStatus.CREATED);
    }

    @PostMapping("v1/account/{id}/entry")
    public ResponseEntity<Void> processEntry(@PathVariable("id") UUID id,
                                             @RequestBody EntryCreationDTO dto) {
        entryProcessorService.processNewEntry(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("v1/account/{branch}/{account}")
    public ResponseEntity<AccountResponse> findByBranchAndAccountWithAllEntries(
            @PathVariable("branch") Integer branch,
            @PathVariable("account") Long account
    ) {
        Account accountFound = accountCrudService.findByBranchAndAccountWithAllEntries(branch, account);
        return new ResponseEntity<>(toResponse(accountFound), HttpStatus.OK);
    }

    private List<AccountResponse> toResponse(List<Account> accounts) {
        return accounts.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private AccountResponse toResponse(Account account) {
        var entriesResponse = toEntriesResponse(account);

        return AccountResponse.builder()
                .id(account.getId())
                .branch(account.getBranch())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .entries(entriesResponse)
                .build();
    }

    private List<AccountResponse.EntryResponse> toEntriesResponse(Account account) {
        List<Entry> entries = account.getEntries();
        if (entries == null) {
            return Collections.emptyList();
        }

        List<AccountResponse.EntryResponse> entryResponse = new ArrayList<>(entries.size());
        entries.forEach(e -> {
            var entry = AccountResponse.EntryResponse.builder()
                    .type(e.getEntryType().getValue())
                    .amount(e.getAmount())
                    .id(e.getId())
                    .build();
            entryResponse.add(entry);
        });
        return entryResponse;
    }
}
