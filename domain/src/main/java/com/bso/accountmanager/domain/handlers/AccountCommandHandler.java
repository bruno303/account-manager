package com.bso.accountmanager.domain.handlers;

import com.bso.accountmanager.domain.commands.account.CreateAccountCommand;

public interface AccountCommandHandler {
    void handle(CreateAccountCommand command);
}
