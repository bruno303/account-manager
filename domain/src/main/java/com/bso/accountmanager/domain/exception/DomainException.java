package com.bso.accountmanager.domain.exception;

public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }
    
    public static void throwsWhen(boolean condition, String message) {
        if (condition) throw new DomainException(message);
    }
}
