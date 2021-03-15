package edu.pkch.string;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringPoolTest {

    @Test
    void string_literal_vs_string_constructor() {
        String s1 = "Cat";
        String s3 = new String("Cat");

        assertThat(s1 == s3).isFalse();
        assertThat(s1).isEqualTo(s3);
    }

    @Test
    void only_string_literal() {
        String s1 = "Cat";
        String s2 = "Cat";

        assertThat(s1 == s2).isTrue();
        assertThat(s1).isEqualTo(s2);
    }

    @Test
    void manual_interning() {
        String s1 = "Cat";
        String s2 = new String("Cat");

        assertThat(s1 == s2).isFalse();

        String s3 = s2.intern();

        assertThat(s1 == s3).isTrue();
    }

    @Test
    void string_plus_operator() {
        String a = "a";

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            a += "a";
        }
        long end = System.currentTimeMillis();

        System.out.printf("실행시간: %d\n", end - start);
    }

    @Test
    void stringBuilder_append_operator() {
        String a = "a";

        StringBuilder stringBuilder = new StringBuilder(a);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10_000_000; i++) {
            stringBuilder.append("a");
        }
        long end = System.currentTimeMillis();

        System.out.printf("실행시간: %d\n", end - start);
    }

    @Test
    void stringBuffer_append_operator() {
        String a = "a";

        StringBuffer stringBuffer = new StringBuffer(a);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10_000_000; i++) {
            stringBuffer.append("a");
        }
        long end = System.currentTimeMillis();

        System.out.printf("실행시간: %d\n", end - start);
    }
}
