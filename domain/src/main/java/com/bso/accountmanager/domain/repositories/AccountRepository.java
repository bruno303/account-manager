package com.bso.accountmanager.domain.repositories;

import com.bso.accountmanager.domain.entity.account.Account;

import java.util.Optional;

public interface AccountRepository extends Repository<Account> {

    Optional<Account> findByBranchAndAccount(Integer branch, Long account);
    Optional<Account> findByBranchAndAccountWithAllEntries(Integer branch, Long accountNumber);

}