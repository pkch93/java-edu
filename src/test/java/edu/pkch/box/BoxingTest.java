package edu.pkch.box;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class BoxingTest {

    @Test
    void name() {
        long result = 0;
        long start = System.currentTimeMillis();

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
//            result += Integer.valueOf(i);
            result += i;
        }

        long end = System.currentTimeMillis();
        System.out.println("execute: " + (end - start));
    }
}
