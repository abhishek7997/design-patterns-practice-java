package org.java.designpatterns.singleton;

public class DoubleCheckedSingleton {
    private static volatile DoubleCheckedSingleton instance; // volatile keyword to ensure all threads see latest changes to instance
    private final long id;

    private DoubleCheckedSingleton() {
        id = System.nanoTime();
    }

    public static DoubleCheckedSingleton getInstance() {
        if (instance == null) {
            // multiple threads can pass the first check at the same time, but only a single thread will enter the synchronized block and initialize the instance
            // another check if required, so that other threads can see the initialized instance (thanks to volatile), fail the check and return the instance.
            synchronized (DoubleCheckedSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }

        return instance;
    }

    public long getId() {
        return id;
    }
}
