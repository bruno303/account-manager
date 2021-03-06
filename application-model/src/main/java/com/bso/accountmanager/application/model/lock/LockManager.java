package com.bso.accountmanager.application.model.lock;

public interface LockManager {
    boolean tryLock(Lockeable object);
    void unlock(Lockeable object);
    void lockAndConsume(Lockeable object, Runnable runnable);
    <T> T lockAndProcess(Lockeable object, Callable<T> callable);
}
