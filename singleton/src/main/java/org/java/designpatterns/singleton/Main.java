package org.java.designpatterns.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for(int i=0;i<5;i++) {
            executorService.submit(() -> {
                var instance = NonThreadsafeSingleton.getInstance();
                System.out.println("GOT INSTANCE WITH id: " + instance.getId());
            });
        }

        executorService.shutdown();

        try {
            if (executorService.awaitTermination(15, TimeUnit.SECONDS)) {
                System.out.println("Terminated");
            } else {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
