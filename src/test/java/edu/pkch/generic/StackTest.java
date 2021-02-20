package edu.pkch.generic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StackTest {

    @Test
    @DisplayName("공변 테스트")
    void variance() {
        // given
        Stack<Number> numberStack = new Stack<>();

        // when
        List<Integer> integers = Arrays.asList(1, 2, 3);
        numberStack.pushAll(integers);

        // then
        assertThat(numberStack.stackSize()).isEqualTo(3);
    }

    @Test
    @DisplayName("반공변 테스트")
    void contraVariance() {
        // given
        Stack<Number> numberStack = new Stack<>();
        List<Integer> integers = Arrays.asList(1, 2, 3);
        numberStack.pushAll(integers);

        // when
        List<Object> dst = new ArrayList<>();
        numberStack.popAll(dst);

        // then
        assertThat(dst.size()).isEqualTo(3);
    }
}