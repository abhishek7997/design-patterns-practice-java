import org.java.designpatterns.singleton.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingletonTests {
    private ExecutorService executorService;

    @BeforeEach
    public void setUp() {
        executorService = Executors.newFixedThreadPool(5);
    }

    @Test
    public void nonThreadSafeSingletonTest() throws InterruptedException {
        System.out.println("nonThreadSafeSingletonTest");
        Set<Long> set = ConcurrentHashMap.newKeySet();

        try {
            int threads = 50;
            for(int i=0;i<threads;i++) {
                executorService.submit(() -> {
                    var instance = NonThreadsafeSingleton.getInstance();
//                    System.out.println("Instance Id: " + instance.getId());
                    set.add(instance.getId());
                });
            }
        } finally {
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("NonThreadsafeSingleton::Actual instance count: " + set.size());
//            Assertions.assertNotEquals(1, set.size()); // not always guaranteed to pass
        }
    }

    @Test
    public void doubleCheckedSingletonTest() throws InterruptedException {
        System.out.println("doubleCheckedSingletonTest");
        Set<Long> set = ConcurrentHashMap.newKeySet();

        try {
            int threads = 20;
            for(int i=0;i<threads;i++) {
                executorService.submit(() -> {
                    long start = System.nanoTime();
                    var instance = DoubleCheckedSingleton.getInstance();
                    double duration = (System.nanoTime() - start) / 1_000_000.0;
                    System.out.println("Instance Id: " + instance.getId() + ", time taken: " + String.format("%.3f ms", duration));
                    set.add(instance.getId());
                });
            }
        } finally {
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            Assertions.assertEquals(1, set.size());
        }
    }

    @Test
    public void staticSynchronizedSingletonTest() throws InterruptedException {
        System.out.println("staticSynchronizedSingletonTest");
        Set<Long> set = ConcurrentHashMap.newKeySet();

        try {
            int threads = 20;
            for(int i=0;i<threads;i++) {
                executorService.submit(() -> {
                    long start = System.nanoTime();
                    var instance = StaticSynchronizedSingleton.getInstance();
                    double duration = (System.nanoTime() - start) / 1_000_000.0;
                    System.out.println("Instance Id: " + instance.getId() + ", time taken: " + String.format("%.3f ms", duration));
                    set.add(instance.getId());
                });
            }
        } finally {
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            Assertions.assertEquals(1, set.size());
        }
    }

    @Test
    public void jvmClassLoadedSingletonTest() throws InterruptedException {
        System.out.println("jvmClassLoadedSingletonTest");
        Set<Long> set = ConcurrentHashMap.newKeySet();

        try {
            int threads = 20;
            for(int i=0;i<threads;i++) {
                executorService.submit(() -> {
                    long start = System.nanoTime();
                    var instance = BillPughSingleton.getInstance();
                    double duration = (System.nanoTime() - start) / 1_000_000.0;
                    System.out.println("Instance Id: " + instance.getId() + ", time taken: " + String.format("%.3f ms", duration));
                    set.add(instance.getId());
                });
            }
        } finally {
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            Assertions.assertEquals(1, set.size());
        }
    }

    @Test
    public void enumSingletonTest() throws InterruptedException {
        System.out.println("enumSingletonTest");
        Set<Long> set = ConcurrentHashMap.newKeySet();

        try {
            int threads = 20;
            for(int i=0;i<threads;i++) {
                executorService.submit(() -> {
                    long start = System.nanoTime();
                    var instance = EnumSingleton.getInstance();
                    double duration = (System.nanoTime() - start) / 1_000_000.0;
                    System.out.println("Instance Id: " + instance.getId() + ", time taken: " + String.format("%.3f ms", duration));
                    set.add(instance.getId());
                });
            }
        } finally {
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            Assertions.assertEquals(1, set.size());
        }
    }
}
