package com.bso.accountmanager.application.factory;

import com.bso.accountmanager.domain.entity.account.Account;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

@Component
public class AccountFactory {

    public Account create(UUID id, Integer branch, Long account, BigDecimal balance) {
        return new Account(id, branch, account, Collections.emptyList(), balance);
    }

    public Account create(Integer branch, Long account, BigDecimal balance) {
        return create(UUID.randomUUID(), branch, account, balance);
    }
}
