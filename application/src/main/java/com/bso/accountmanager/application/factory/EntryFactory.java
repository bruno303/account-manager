package com.bso.accountmanager.application.factory;

import com.bso.accountmanager.domain.entity.account.Entry;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class EntryFactory {

    public Entry create(BigDecimal amount, Entry.EntryType entryType) {
        return create(UUID.randomUUID(), amount, entryType);
    }

    private Entry create(UUID id, BigDecimal amount, Entry.EntryType entryType) {
        return new Entry(id, amount, entryType);
    }

}
