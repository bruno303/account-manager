package com.bso.accountmanager.domain.commands.entry;

import com.bso.accountmanager.domain.entity.account.Entry;

import java.math.BigDecimal;

public class CreateEntryCommand {
    private final Integer branch;
    private final Long accountNumber;
    private final BigDecimal amount;
    private final Entry.EntryType entryType;

    public CreateEntryCommand(Integer branch, Long accountNumber, BigDecimal amount, Entry.EntryType entryType) {
        this.branch = branch;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.entryType = entryType;
    }

    public Integer getBranch() {
        return branch;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Entry.EntryType getEntryType() {
        return entryType;
    }
}
