package com.bso.accountmanager.infrastructure.repositories.jpa.impl;

import java.util.UUID;

import com.bso.accountmanager.infrastructure.entities.Entry;
import com.bso.accountmanager.infrastructure.repositories.PersistenceEntryRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersistenceEntryRepositoryJpaImpl extends JpaRepository<Entry, UUID>, PersistenceEntryRepository {
    
}
