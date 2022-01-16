package com.bso.accountmanager.domain.handlers;

import com.bso.accountmanager.domain.entity.account.Account;
import com.bso.accountmanager.domain.querys.AccountWithAllEntriesQuery;
import com.bso.accountmanager.domain.querys.AllAccountsQuery;

import java.util.List;

public interface AccountQueryHandler {
    Account handle(AccountWithAllEntriesQuery query);
    List<Account> handle(AllAccountsQuery query);
}
