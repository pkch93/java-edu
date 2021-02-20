package edu.pkch.immutable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class CounterTest {

    @Test
    @DisplayName("[가변] 여러 스레드에서 값을 변경")
    void mutableInMultiThread() {
        // given
        Counter counter = new Counter(0);

        // when
        List<CompletableFuture<Integer>> futures = IntStream.range(0, 5)
                .mapToObj(idx -> CompletableFuture.supplyAsync(() -> {
                    counter.addOne();
                    return counter.getCount();
                }))
                .collect(Collectors.toList());

        // then
        assertThat(futures)
                .extracting(Future::get)
                .hasSize(5);
    }

    @Test
    @DisplayName("[가변] synchronized 적용하여 여러 스레드에서 값 변경")
    void mutableWithSynchronized() {
        // given
        SynchronizedCounter counter = new SynchronizedCounter(0);

        // when
        List<CompletableFuture<Integer>> futures = IntStream.range(0, 5)
                .mapToObj(idx -> CompletableFuture.supplyAsync(counter::addOne))
                .collect(Collectors.toList());

        // then
        assertThat(futures)
                .extracting(Future::get)
                .hasSize(5)
                .doesNotContainSequence(1, 2, 3, 4, 5);
    }

    @Test
    @DisplayName("[불변] 여러 스레드에서 값을 변경")
    void immutableCounterWithMultiThread() {
        // given
        ImmutableCounter counter = new ImmutableCounter(0);

        // when
        List<CompletableFuture<ImmutableCounter>> futures = IntStream.range(0, 5)
                .mapToObj(idx -> CompletableFuture.supplyAsync(counter::addOne))
                .collect(Collectors.toList());

        // then
        assertThat(futures)
                .extracting(Future::get)
                .hasSize(5)
                .extracting(ImmutableCounter::getCount)
                .containsOnly(1);
    }
}