package org.java.designpatterns.singleton;

public class StaticBlockInitializedSingleton {
    private static StaticBlockInitializedSingleton instance;
    private final long id;

    static {
        try {
            instance = new StaticBlockInitializedSingleton();
        } catch (Exception ignored) { }
    }

    private StaticBlockInitializedSingleton() {
        id = System.nanoTime();
    }

    public static StaticBlockInitializedSingleton getInstance() {
        return instance;
    }

    public long getId() {
        return id;
    }
}
