package com.bso.accountmanager.application.dto.entry;

import com.bso.accountmanager.domain.entity.account.Entry;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EntryCreationDTO {

    private final Integer branch;
    private final Long account;
    private final BigDecimal amount;
    private final Entry.EntryType entryType;

}
