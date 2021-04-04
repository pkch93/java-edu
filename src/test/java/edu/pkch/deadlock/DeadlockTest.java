package edu.pkch.deadlock;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class DeadlockTest {

    @Test
    void not_deadlock() {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        set.addObserver((s, e) -> System.out.println(e));

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }

    @Test
    void throw_ConcurrentModificationException() {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
                if (element == 23) {
                    set.removeObserver(this);
                }
            }
        });

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }

    /**
     * 메인 스레드 뿐만 아니라 백그라운드 스레드에서 실행되는 callback 메서드가 락을 획득
     * 때문에 deadlock이 발생한다.
     *
     * @see {Effective Java 3/e. item 79}
     */
    @Test
    void deadlock() {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
                if (element == 23) {
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    try {
                        executorService.submit(() -> set.removeObserver(this)).get();
                    } catch (ExecutionException | InterruptedException e) {
                        throw new AssertionError(e);
                    } finally {
                        executorService.shutdown();
                    }
                }
            }
        });

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }
}