package com.bso.accountmanager.infrastructure.repositories;

import com.bso.accountmanager.infrastructure.entities.Account;

import java.util.Optional;

public interface PersistenceAccountRepository extends PersistenceRepository<Account> {

    Optional<Account> findByBranchAndAccountNumber(Integer branch, Long account);

}
