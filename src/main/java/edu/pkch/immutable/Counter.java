package edu.pkch.immutable;

public class Counter {
    private int count;

    public Counter(int count) {
        this.count = count;
    }

    public void addOne() {
        this.count += 1;
    }

    public int getCount() {
        return this.count;
    }
}
