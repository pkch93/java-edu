package edu.pkch.concurrency.concurrent;

import java.util.Arrays;
import java.util.List;

public class Sender implements Runnable {
    private SyncData syncData;

    public Sender(SyncData syncData) {
        this.syncData = syncData;
    }

    @Override
    public void run() {
        List<String> packets = Arrays.asList(
                "First packet",
                "Second packet",
                "Third packet",
                "Fourth packet",
                "End"
        );

        packets.forEach(packet -> syncData.send(packet));
    }
}
