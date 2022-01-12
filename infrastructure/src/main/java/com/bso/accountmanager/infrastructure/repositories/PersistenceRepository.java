package com.bso.accountmanager.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bso.accountmanager.infrastructure.entities.PersistenceEntity;

public interface PersistenceRepository<T extends PersistenceEntity> {
    Optional<T> findById(UUID id);
    List<T> findAll();
    T save(T entity);
    T saveAndFlush(T entity);
    void delete(T entity);
}
