package com.bso.accountmanager.infrastructure.mapper;

import com.bso.accountmanager.infrastructure.entities.Entry;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntryMapper {

    public List<Entry> toPersistenceEntries(List<com.bso.accountmanager.domain.entity.account.Entry> entries) {
        if (entries == null) {
            return null;
        }
        return entries.stream().map(this::toPersistenceEntry).collect(Collectors.toList());
    }

    private Entry toPersistenceEntry(com.bso.accountmanager.domain.entity.account.Entry entry) {
        var persistenceEntry = new Entry();
        persistenceEntry.setId(entry.getId());
        persistenceEntry.setEntryType(entry.getEntryType().getValue());
        persistenceEntry.setAmount(entry.getAmount());
        return persistenceEntry;
    }

    public List<com.bso.accountmanager.domain.entity.account.Entry> toDomainEntries(List<Entry> entries) {
        if (entries == null) {
            return null;
        }
        return entries.stream().map(this::toDomainEntry).collect(Collectors.toList());
    }

    private com.bso.accountmanager.domain.entity.account.Entry toDomainEntry(Entry entry) {
        var entryType = com.bso.accountmanager.domain.entity.account.Entry.EntryType.of(entry.getEntryType());
        return new com.bso.accountmanager.domain.entity.account.Entry(entry.getId(), entry.getAmount(), entryType);
    }
}
