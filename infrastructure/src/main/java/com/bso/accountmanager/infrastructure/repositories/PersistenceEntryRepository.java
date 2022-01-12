package com.bso.accountmanager.infrastructure.repositories;

import com.bso.accountmanager.infrastructure.entities.Entry;

import java.util.List;
import java.util.UUID;

public interface PersistenceEntryRepository extends PersistenceRepository<Entry> {

    List<Entry> findByAccountId(UUID contractId);

}
