package com.bso.accountmanager.registration.exception;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountManagerResponseError {
    private final LocalDate date;
    private final String message;
}
