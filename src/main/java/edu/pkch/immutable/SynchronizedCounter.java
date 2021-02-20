package edu.pkch.immutable;

public class SynchronizedCounter {

    private int count;

    public SynchronizedCounter(int counter) {
        this.count = counter;
    }

    public synchronized int addOne() {
        this.count += 1;
        return this.count;
    }
}
