package edu.pkch.concurrency;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

class SynchronizedNumberTest {

    @Test
    @DisplayName("synchronized method의 락 단위는 객체 단위가 아니라 메서드 단위이다.")
    void synchronizedMethod() {
        // given
        SynchronizedNumber synchronizedNumber = new SynchronizedNumber(1);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // when
        IntStream.rangeClosed(1, 100)
                .forEach(number -> {
                    executorService.submit(() -> System.out.println(synchronizedNumber.syncGet()));
                    synchronizedNumber.syncSet(number);
                });
    }

    @Test
    void synchronizedMethod_versus_nonSynchronized() {
        SynchronizedNumber synchronizedNumber = new SynchronizedNumber(1);

        long start = System.nanoTime();
        IntStream.rangeClosed(1, 100_000_000)
                .forEach(notUse -> synchronizedNumber.get());
        long end = System.nanoTime();

        System.out.println("nonSynchronized: " + (end - start));

        start = System.nanoTime();
        IntStream.rangeClosed(1, 100_000_000)
                .forEach(notUse -> synchronizedNumber.syncGet());
        end = System.nanoTime();

        System.out.println("synchronized: " + (end - start));
    }
}