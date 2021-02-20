package edu.pkch.optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class OptionalTest {

    @Test
    @DisplayName("Optional에는 Functor의 map이 있다.")
    void map() {
        // given
        String firstName = "Park";
        String lastName = "GyeongCheol";
        Optional<String> maybeFirstName = Optional.ofNullable(firstName);

        // when
        Optional<String> actual = maybeFirstName.map(first -> first + lastName);

        // then
        assertThat(actual.get()).isEqualTo(firstName + lastName);
    }

    @Test
    @DisplayName("Optional에는 Monad의 flatMap이 있다.")
    void flatMap() {
        // given
        String firstName = "Park";
        Optional<String> maybeFirstName = Optional.ofNullable(firstName);

        String lastName = "GyeongCheol";
        Optional<String> maybeLastName = Optional.ofNullable(lastName);

        // when
        Optional<String> actual = maybeFirstName.flatMap(first -> maybeLastName.map(last -> first + last));

        // then
        assertThat(actual.get()).isEqualTo(firstName + lastName);
    }

    @Test
    @DisplayName("Optional은 Monad의 왼쪽 항등 법칙을 위반한다.")
    void leftIdentity() {
        // given
        Function<Integer, Optional<String>> f = i -> Optional.of(i == null ? "NaN" : i.toString());

        // when
        Optional<String> actualLeft = f.apply(null);
        Optional<String> actualRight = Optional.<Integer>ofNullable(null).flatMap(f);

        // then
        assertThat(actualLeft.get()).isEqualTo("NaN");
        assertThat(actualLeft.isPresent()).isEqualTo(true);
        assertThat(actualRight.isPresent()).isEqualTo(false);
    }
}
