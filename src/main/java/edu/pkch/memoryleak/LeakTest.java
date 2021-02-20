package edu.pkch.memoryleak;

import java.util.ArrayList;
import java.util.List;

public class LeakTest {
    public List<Double> numbers = new ArrayList<>();

    public void populate() {
        for (int i = 0; i < 500_000_000; i++) {
            numbers.add(Math.random());
        }
    }
}
