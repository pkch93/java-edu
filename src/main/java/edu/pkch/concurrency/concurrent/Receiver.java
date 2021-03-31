package edu.pkch.concurrency.concurrent;

public class Receiver implements Runnable {
    private SyncData syncData;

    public Receiver(SyncData syncData) {
        this.syncData = syncData;
    }

    @Override
    public void run() {
        for (String received = syncData.receive(); !"End".equals(received); received = syncData.receive()) {
            System.out.println(received);
        }
    }
}
