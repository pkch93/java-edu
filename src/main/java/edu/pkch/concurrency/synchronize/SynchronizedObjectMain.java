package edu.pkch.concurrency.synchronize;

import edu.pkch.concurrency.synchronize.Data;
import edu.pkch.concurrency.synchronize.Receiver;
import edu.pkch.concurrency.synchronize.Sender;

public class SynchronizedObjectMain {

    public static void main(String[] args) {
        Data data = new Data();
        Thread sender = new Thread(new Sender(data));
        Thread receiver = new Thread(new Receiver(data));

        sender.start();
        receiver.start();
    }
}
