package edu.pkch.stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class ParallelStreamTest {

    @Test
    void name() {
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));

        // 순차 스트림 처리 시간
        long timeSequence = sequenceTest(list);

        // 병렬 스트림 처리 시간
        long timeParallel = parallelTest(list);

        if (timeSequence < timeParallel) {
            System.out.println("결과: 순차 처리가 더 빠름");
        } else {
            System.out.println("결과: 병렬 처리가 더 빠름");
        }
    }

    private static String work(int value) {
        try {
            Thread.sleep(1);
            System.out.println(value);
            return value + "";
        } catch (InterruptedException e) {
            return value + "";
        }
    }

    private static long sequenceTest(List<Integer> list) {
        long start = System.nanoTime();
        String actual = list.stream().map(ParallelStreamTest::work).collect(Collectors.joining(", "));
        long end = System.nanoTime();
        long runTime = end - start;

        System.out.printf("순차처리 결과: %s\n", actual);
        return runTime;
    }

    private static long parallelTest(List<Integer> list) {
        long start = System.nanoTime();
        list.stream().parallel().forEach(ParallelStreamTest::work);
        long end = System.nanoTime();
        long runTime = end - start;
        return runTime;
    }
}
