package edu.pkch.generic;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class GenericTest {

    @Test
    void arrayVariance() {
        Object[] objects = new Long[1];
        objects[0] = "hello";
    }

    @Test
    void uncheckedCall() {
        Set words = new HashSet();
        words.add("hello");
        words.add(1);

        assertThat(words.contains("hello")).isEqualTo(true);
        assertThat(words.contains(1)).isEqualTo(true);
    }

    @Test
    void uncheckedCast() {
        Map<String, String> map = (Map<String, String>) getMap();
    }

    private Map getMap() {
        return new HashMap<String, String>();
    }

    @Test
    void heapPollution() {
        List list = toList(String.class, "1", "2");
        Iterator<String> iter = list.iterator();

        while (iter.hasNext())
        {
            String str = iter.next();
            System.out.println(str);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> toList(Class<T> clazz, T... elements) {
        return Arrays.asList(elements);
    }
}
