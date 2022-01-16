package com.bso.accountmanager.domain.handlers;

import com.bso.accountmanager.domain.commands.entry.CreateEntryCommand;

public interface EntryCommandHandler {
    void handle(CreateEntryCommand command);
}
