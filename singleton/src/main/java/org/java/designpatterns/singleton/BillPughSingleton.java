package org.java.designpatterns.singleton;

public class BillPughSingleton {
    // lazy loaded, thread safe, by relying on JVM's class loading behavior instead of using explicit synchronization.
    // Idea: A class is loaded only when it is first used, and class loading is thread-safe. Class initialization is synchronized by the JVM.
    private final long id;

    // Initialization-on-Demand Holder
    private static class SingletonHelper {
        // only loaded in memory by JVM when referenced first time.
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    private BillPughSingleton() {
        id = System.nanoTime();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public long getId() {
        return id;
    }
}
