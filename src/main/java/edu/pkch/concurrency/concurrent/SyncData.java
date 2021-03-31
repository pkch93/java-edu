package edu.pkch.concurrency.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SyncData {
    private BlockingQueue<String> packets;

    public SyncData() {
        this.packets = new ArrayBlockingQueue<>(16);
    }

    public void send(String packet) {
        this.packets.offer(packet);
    }

    public String receive() {
        try {
            return packets.take();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
