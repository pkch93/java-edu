package edu.pkch.concurrency;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

class ConcurrentCollectionsTest {

    @Test
    void synchronizedMap() {
        // given
        Map<String, Integer> counts = new HashMap<>();
        Map<String, Integer> synchronizedMap = Collections.synchronizedMap(counts);

        synchronizedMap.put("park", 100);
        synchronizedMap.put("lee", 200);
        synchronizedMap.put("kim", 300);

        // when
        List<String> names = Arrays.asList("park", "lee", "kim");

        long start = System.nanoTime();
        IntStream.rangeClosed(1, 100_000_000)
                .forEach(number -> {
                    int index = number % 3;
                    synchronizedMap.get(names.get(index));
                });
        long end = System.nanoTime();

        System.out.println("synchronizedMap execute time: " + (end - start) + "ns");
    }

    @Test
    void concurrentHashMap() {
        // given
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

        concurrentHashMap.put("park", 100);
        concurrentHashMap.put("lee", 200);
        concurrentHashMap.put("kim", 300);

        // when
        List<String> names = Arrays.asList("park", "lee", "kim");

        long start = System.nanoTime();
        IntStream.rangeClosed(1, 100_000_000)
                .forEach(number -> {
                    int index = number % 3;
                    concurrentHashMap.get(names.get(index));
                });
        long end = System.nanoTime();

        System.out.println("concurrentHashMap execute time: " + (end - start) + "ns");
    }

    @Test
    void hashMap() {
        // given
        Map<String, Integer> map = new HashMap<>();

        map.put("park", 100);
        map.put("lee", 200);
        map.put("kim", 300);

        // when
        List<String> names = Arrays.asList("park", "lee", "kim");

        long start = System.nanoTime();
        IntStream.rangeClosed(1, 100_000_000)
                .forEach(number -> {
                    int index = number % 3;
                    map.get(names.get(index));
                });
        long end = System.nanoTime();

        System.out.println("HashMap execute time: " + (end - start) + "ns");
    }
}
