package org.java.designpatterns.singleton;

public class NonThreadsafeSingleton {
    private static NonThreadsafeSingleton instance;
    private final long id;

    private NonThreadsafeSingleton() {
        id = System.nanoTime();
//        System.out.println("New NonThreadsafeSingleton instance with id: " + id);
    }

    public static NonThreadsafeSingleton getInstance() {
        // laze initialization, not thread safe here
        if (instance == null) {
            instance = new NonThreadsafeSingleton();
        }

        return instance;
    }

    public long getId() {
        return id;
    }
}
