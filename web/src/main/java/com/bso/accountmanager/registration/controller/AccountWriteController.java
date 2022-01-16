package com.bso.accountmanager.registration.controller;

import com.bso.accountmanager.application.service.AccountQueryHandlerImpl;
import com.bso.accountmanager.domain.commands.account.CreateAccountCommand;
import com.bso.accountmanager.domain.commands.entry.CreateEntryCommand;
import com.bso.accountmanager.domain.handlers.AccountCommandHandler;
import com.bso.accountmanager.domain.handlers.EntryCommandHandler;
import com.bso.accountmanager.registration.config.AccountManagerController;
import com.bso.accountmanager.registration.dto.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@AccountManagerController
@RequiredArgsConstructor
public class AccountWriteController {

    private final AccountQueryHandlerImpl queryHandler;
    private final AccountCommandHandler accountCommandHandler;
    private final EntryCommandHandler entryCommandHandler;

    @PostMapping("v1/account")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody CreateAccountCommand command) {
        accountCommandHandler.handle(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("v1/account/{id}/entry")
    public ResponseEntity<Void> processEntry(@PathVariable("id") UUID id,
                                             @RequestBody CreateEntryCommand command) {
        entryCommandHandler.handle(command);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
