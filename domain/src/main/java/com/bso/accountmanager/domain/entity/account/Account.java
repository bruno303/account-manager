package com.bso.accountmanager.domain.entity.account;

import com.bso.accountmanager.domain.entity.AbstractAggregateRoot;
import com.bso.accountmanager.domain.entity.AggregateRoot;
import com.bso.accountmanager.domain.entity.Entity;
import com.bso.accountmanager.domain.exception.DomainException;
import com.bso.accountmanager.domain.exception.InsuficientBalanceException;
import com.bso.accountmanager.domain.utils.BigDecimalUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Account extends AbstractAggregateRoot
        implements Entity, AggregateRoot {

    private final UUID id;
    private final Integer branch;
    private final Long accountNumber;
    private List<Entry> entries;
    private BigDecimal balance;

    public Account(UUID id, Integer branch, Long accountNumber, List<Entry> entries, BigDecimal balance) {
        this.id = id;
        this.branch = branch;
        this.accountNumber = accountNumber;
        this.entries = entries;
        this.balance = balance;
        validate();
    }

    public Account(UUID id, Integer branch, Long accountNumber, BigDecimal balance) {
        this(id, branch, accountNumber, new ArrayList<>(), balance);
    }

    @Override
    public UUID getId() {
        return id;
    }

    private void validate() {
        DomainException.throwsWhen(id == null, "Account id can't be null");
    }

    public Integer getBranch() {
        return branch;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void processEntry(Entry entry) {
        if (entry.isDebt()) {
            validateBalance(entry);
        }

        this.balance = this.balance.add(entry.getRealAmount());
        addEntry(entry);
    }

    private void addEntry(Entry entry) {
        if (entries == null) {
            entries = new ArrayList<>();
        }
        entries.add(entry);
    }

    private void validateBalance(Entry entry) {
        if (BigDecimalUtils.firstIsGreaterThanSecond(entry.getAmount(), this.balance)) {
            throw new InsuficientBalanceException(branch, accountNumber, balance, entry.getAmount());
        }
    }
}
