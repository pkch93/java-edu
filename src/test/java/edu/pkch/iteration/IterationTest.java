package edu.pkch.iteration;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

class IterationTest {

    @Test
    void foreach_iterable() {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        // when
        for (int number: numbers) {
            assertThat(number <= 3).isEqualTo(true);
        }
    }

    @Test
    void foreach_stream_adaptor() {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        // when
        Stream<Integer> numberStream = getStream(numbers);

        // then
        numberStream.forEach(number -> assertThat(number <= 3).isTrue());
    }

    private static <T> Stream<T> getStream(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
