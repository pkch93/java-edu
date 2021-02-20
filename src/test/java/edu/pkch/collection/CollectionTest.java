package edu.pkch.collection;

import org.junit.jupiter.api.Test;

import java.util.List;

class CollectionTest {

    @Test
    void name() {
        List<String> list = Collection.list();
        list.add("k");

        List<Integer> list2 = Collection.list();
        list2.add(1);
    }
}