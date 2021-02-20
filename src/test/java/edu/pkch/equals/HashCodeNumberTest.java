package edu.pkch.equals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class HashCodeNumberTest {

    @Test
    @DisplayName("hashCode가 동일한 값을 리턴할 때")
    void sameHashCodeNumber() {
        // given
        long start = System.currentTimeMillis();
        Map<SameHashCodeNumber, Integer> sameHashCodeNumbers = IntStream.range(1, 10_000)
                .mapToObj(SameHashCodeNumber::new)
                .collect(Collectors.toMap(sameHashCodeNumber -> sameHashCodeNumber, SameHashCodeNumber::getNumber));

        // when
        Integer actual = sameHashCodeNumbers.get(new SameHashCodeNumber(100));
        long end = System.currentTimeMillis();

        // then
        System.out.println(
                String.format("총 실행 시간: %d", end - start)
        );
        assertThat(actual).isEqualTo(100);
    }

    @Test
    @DisplayName("hashCode가 다른 값을 리턴할 때")
    void diffHashCodeNumber() {
        // given
        long start = System.currentTimeMillis();
        Map<SameHashCodeNumber, Integer> sameHashCodeNumbers = IntStream.range(1, 10_000_000)
                .mapToObj(SameHashCodeNumber::new)
                .collect(Collectors.toMap(sameHashCodeNumber -> sameHashCodeNumber, SameHashCodeNumber::getNumber));

        // when
        Integer actual = sameHashCodeNumbers.get(new SameHashCodeNumber(5_000));
        long end = System.currentTimeMillis();

        // then
        System.out.println(
                String.format("총 실행 시간: %d", end - start)
        );
        assertThat(actual).isEqualTo(5000);
    }
}