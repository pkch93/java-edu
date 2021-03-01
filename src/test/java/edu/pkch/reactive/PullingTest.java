package edu.pkch.reactive;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class PullingTest {

    @Test
    @DisplayName("Iterable은 pull 방식의 대표적인 시퀀스이다.")
    void pulling() {
        // given
        Iterable<Integer> numbers = () -> new Iterator<Integer>() {
            List<Integer> temp = new ArrayList<>(
                    Arrays.asList(1, 2, 3, 4, 5)
            );

            @Override
            public boolean hasNext() {
                return !temp.isEmpty();
            }

            @Override
            public Integer next() {
                return temp.remove(0);
            }
        };

        // when & then
        for (int number : numbers) {
            assertThat(number <= 5).isTrue();
        }
    }

    @Test
    @DisplayName("[Iterable과 비동기] Iterable은 pull 방식의 대표적인 시퀀스이다.")
    void pulling_with_async() throws InterruptedException {
        // given
        Iterable<Integer> numbers = () -> new Iterator<Integer>() {
            List<Integer> temp = new ArrayList<>(
                    Arrays.asList(1, 2, 3, 4, 5)
            );

            @Override
            public boolean hasNext() {
                return !temp.isEmpty();
            }

            @Override
            public Integer next() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Integer remove = temp.remove(0);
                System.out.println(remove);
                return remove;
            }
        };

        // when
        Iterator<Integer> iterator = numbers.iterator();

        // then
        IntStream.rangeClosed(1, 5)
                .forEach(notUse -> CompletableFuture.runAsync(iterator::next));

        Thread.sleep(3000);
    }
}
