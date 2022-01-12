package com.bso.accountmanager.domain.entity;

import com.bso.accountmanager.domain.events.Event;

import java.util.List;

public interface AggregateRoot {
    List<Event> getDomainEvents();
}
