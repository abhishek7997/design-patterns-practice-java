package org.java.designpatterns.singleton;

public class StaticSynchronizedSingleton {
    private static StaticSynchronizedSingleton instance; // volatile keyword to ensure all threads see latest changes to instance
    private final long id;

    private StaticSynchronizedSingleton() {
        id = System.nanoTime();
    }

    public synchronized static StaticSynchronizedSingleton getInstance() {
        // safe, but has synchronization overhead even after instance has been initialized, wasting cpu time.
        if (instance == null) {
            instance = new StaticSynchronizedSingleton();
        }

        return instance;
    }

    public long getId() {
        return id;
    }
}
