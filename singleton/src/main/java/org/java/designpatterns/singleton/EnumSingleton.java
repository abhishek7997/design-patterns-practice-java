package org.java.designpatterns.singleton;

// most preferred method of creating singleton, but limits abstraction/inheritance
// JVM guarantees only single instance of an enum is ever created.
public enum EnumSingleton {
    INSTANCE;
    private final long id;

    EnumSingleton() {
        id = System.nanoTime();
    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }

    public long getId() { return id; }
}
