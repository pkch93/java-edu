package edu.pkch.collection;

import java.util.*;

public class Collection {
    public static final List LIST = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public static <T> List<T> list() {
        return (List<T>) LIST;
    }
}
