package com.bso.accountmanager.infrastructure.repositories.jpa.impl;

import com.bso.accountmanager.infrastructure.entities.Account;
import com.bso.accountmanager.infrastructure.repositories.PersistenceAccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersistenceAccountRepositoryJpaImpl extends JpaRepository<Account, UUID>, PersistenceAccountRepository {
    
}
