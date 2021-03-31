package edu.pkch.concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

class SemaphoreTest {

    @Test
    void semaphore() throws InterruptedException {
        SemaphoreNumber semaphoreNumber = new SemaphoreNumber(1, new Semaphore(4));
        ExecutorService executorService = Executors.newFixedThreadPool(16);

        CountDownLatch latch = new CountDownLatch(1);

        IntStream.rangeClosed(1, 10_000)
                .forEach(number -> {
                    executorService.execute(semaphoreNumber::printNumber);

                    if (number == 10_000) {
                        latch.countDown();
                    }
                });

        latch.await();
    }

    private static class SemaphoreNumber {
        private final int number;
        private final Semaphore semaphore;

        public SemaphoreNumber(int number, Semaphore semaphore) {
            this.number = number;
            this.semaphore = semaphore;
        }

        public void printNumber() {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("semaphore count: %d\n", semaphore.availablePermits());
            semaphore.release();
        }
    }
}
