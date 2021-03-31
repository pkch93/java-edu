package edu.pkch.concurrency;

public class SynchronizedNumber {
    private int number;

    public SynchronizedNumber(int number) {
        this.number = number;
    }

    public int get() {
        return number;
    }

    public synchronized int syncGet() {
        return number;
    }

    public synchronized void syncSet(int number) {
        this.number = number;
    }
}
