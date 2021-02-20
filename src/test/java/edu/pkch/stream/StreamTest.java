package edu.pkch.stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamTest {

    @Test
    @DisplayName("[Stream] Stream은 Monad의 flatMap이 있다.")
    void flatMap() {
        // given
        List<List<String>> strs = Arrays.asList(
                Arrays.asList("1", "2", "3"),
                Arrays.asList("4", "5", "6"),
                Arrays.asList("7", "8", "9", "10")
        );

        // when
        String actual = strs.stream()
                .flatMap(Collection::stream)
                .map(str -> str + " ")
                .collect(Collectors.joining());

        // then
        assertThat(actual).isEqualTo("1 2 3 4 5 6 7 8 9 10 ");
    }

    @Test
    @DisplayName("[IntStream] Stream은 Monad의 flatMap이 있다.")
    void flatMap2() {
        // given
        List<Integer> a = Arrays.asList(1, 2, 3);
        List<Integer> b = Arrays.asList(4, 5, 6);
        List<Integer> c = Arrays.asList(7, 8, 9, 10);

        // when
        int actual = Stream.of(a.stream(), b.stream(), c.stream())
                .flatMapToInt(stream -> stream.mapToInt(Integer::intValue))
                .reduce(0, Integer::sum);

        // then
        assertThat(actual).isEqualTo(55);
    }
}
