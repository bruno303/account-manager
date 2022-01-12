package com.bso.accountmanager.infrastructure.entities;

import java.io.Serializable;
import java.util.UUID;

public interface PersistenceEntity extends Serializable {
    
    UUID getId();

}
