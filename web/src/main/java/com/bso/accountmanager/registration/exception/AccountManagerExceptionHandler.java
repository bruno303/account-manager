package com.bso.accountmanager.registration.exception;

import com.bso.accountmanager.domain.exception.AccountNotFoundException;
import com.bso.accountmanager.domain.exception.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class AccountManagerExceptionHandler {

    @ExceptionHandler(value = { AccountNotFoundException.class })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public AccountManagerResponseError domainNotFoundException(Exception ex) {
        return new AccountManagerResponseError(LocalDate.now(), ex.getMessage());
    }

    @ExceptionHandler(value = { DomainException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public AccountManagerResponseError domainException(DomainException ex) {
        return new AccountManagerResponseError(LocalDate.now(), ex.getMessage());
    }

}
