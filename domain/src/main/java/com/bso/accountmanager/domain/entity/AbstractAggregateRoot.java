package com.bso.accountmanager.domain.entity;

import com.bso.accountmanager.domain.events.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractAggregateRoot implements AggregateRoot {

    private final List<Event> domainEvents = new ArrayList<>();

    @Override
    public List<Event> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public void addEvent(Event event) {
        domainEvents.add(event);
    }
}
