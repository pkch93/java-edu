package edu.pkch.concurrency.concurrent;

public class ConcurrentObjectMain {

    public static void main(String[] args) {
        SyncData data = new SyncData();
        Thread sender = new Thread(new Sender(data));
        Thread receiver = new Thread(new Receiver(data));

        sender.start();
        receiver.start();
    }
}
