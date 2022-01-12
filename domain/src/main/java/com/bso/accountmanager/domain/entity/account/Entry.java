package com.bso.accountmanager.domain.entity.account;

import com.bso.accountmanager.domain.entity.Entity;
import com.bso.accountmanager.domain.exception.DomainException;
import com.bso.accountmanager.domain.utils.BigDecimalUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

public class Entry implements Entity {

    public enum EntryType {
        C("C"), D("D");

        private String value;

        EntryType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static EntryType of(String value) {
            return Arrays.stream(EntryType.values())
                    .filter(e -> e.getValue().equals(value))
                    .findAny()
                    .orElseThrow();
        }
    }
    
    private UUID id;
    private BigDecimal amount;
    private EntryType entryType;

    public Entry(UUID id, BigDecimal amount, EntryType entryType) {
        this.id = id;
        this.amount = amount;
        this.entryType = entryType;
        validate();
    }

    private void validate() {
        DomainException.throwsWhen(id == null, "Entry id can't be null");
        DomainException.throwsWhen(BigDecimalUtils.isNegative(amount), "Entry amount can't be negative");
    }

    @Override
    public UUID getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public EntryType getEntryType() {
        return entryType;
    }

    public boolean isDebt() {
        return EntryType.D.equals(entryType);
    }

    public BigDecimal getRealAmount() {
        return isDebt() ? this.amount.negate() : this.amount;
    }
}
