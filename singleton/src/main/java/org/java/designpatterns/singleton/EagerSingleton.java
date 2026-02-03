package org.java.designpatterns.singleton;

public class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();
    private final long id;

    private EagerSingleton() {
        id = System.nanoTime();
    }

    public static EagerSingleton getInstance() {
        return instance;
    }

    public long getId() {
        return id;
    }
}
