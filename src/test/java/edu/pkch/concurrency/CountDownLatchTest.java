package edu.pkch.concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

class CountDownLatchTest {

    @Test
    void countDownLatch() throws InterruptedException {
        // given
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(() -> {
            try {
                Thread.sleep(3000);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        countDownLatch.await();
        assertThat(countDownLatch.getCount()).isEqualTo(0);
    }
}
