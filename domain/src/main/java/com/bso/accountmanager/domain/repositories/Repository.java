package com.bso.accountmanager.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bso.accountmanager.domain.entity.Entity;

public interface Repository<T extends Entity> {
    
    Optional<T> findById(UUID id);
    List<T> findAll();
    T save(T entity);
    T saveAndFlush(T entity);
    void delete(T entity);

}
