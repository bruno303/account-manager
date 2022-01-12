package com.bso.accountmanager.domain.events;

import java.util.concurrent.CompletableFuture;

public interface EventHandler<T extends Event> {
    CompletableFuture<Void> handle(T eventData);
}