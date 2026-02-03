import org.java.designpatterns.singleton.EagerSingleton;
import org.java.designpatterns.singleton.NonThreadsafeSingleton;
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
        Set<Long> set = ConcurrentHashMap.newKeySet();

        try {
            int threads = 50;
            for(int i=0;i<threads;i++) {
                executorService.submit(() -> {
                    var instance = NonThreadsafeSingleton.getInstance();
//                    System.out.println("GOT INSTANCE WITH id: " + instance.getId());
                    set.add(instance.getId());
                });
            }
        } finally {
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("NonThreadsafeSingleton::Actual instance count: " + set.size());
            Assertions.assertNotEquals(1, set.size());
        }
    }

    @Test
    public void eagerSingletonTest() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Set<Long> set = ConcurrentHashMap.newKeySet();

        try {
            int threads = 10;
            for(int i=0;i<threads;i++) {
                executorService.submit(() -> {
                    var instance = EagerSingleton.getInstance();
                    System.out.println("GOT INSTANCE WITH id: " + instance.getId());
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
