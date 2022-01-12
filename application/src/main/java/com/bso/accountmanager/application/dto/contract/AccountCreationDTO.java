package com.bso.accountmanager.application.dto.contract;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountCreationDTO {
    private final Integer branch;
    private final Long account;
    private final BigDecimal balance;
}
