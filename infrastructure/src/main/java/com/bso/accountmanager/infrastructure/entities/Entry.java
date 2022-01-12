package com.bso.accountmanager.infrastructure.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "ENTRY")
@Getter
@Setter
public class Entry implements PersistenceEntity {
    
    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "AMOUNT", nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Column(name = "ENTRY_TYPE", nullable = false, length = 1)
    private String entryType;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private UUID accountId;
}
