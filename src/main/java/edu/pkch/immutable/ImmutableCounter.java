package edu.pkch.immutable;

public class ImmutableCounter {

    private final int count;

    public ImmutableCounter(int count) {
        this.count = count;
    }

    public ImmutableCounter addOne() {
        return new ImmutableCounter(count + 1);
    }

    public int getCount() {
        return this.count;
    }
}
