package com.bso.accountmanager.infrastructure.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ACCOUNT")
@Getter
@Setter
public class Account implements PersistenceEntity {

    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "BRANCH", nullable = false)
    private Integer branch;

    @Column(name = "ACCOUNT", nullable = false)
    private Long accountNumber;

    @Column(name = "BALANCE", nullable = false, precision = 12, scale = 2)
    private BigDecimal balance;

    @Transient
    private List<Entry> entries;
}
