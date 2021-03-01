package edu.pkch.reactive;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

class PushTest {

    @Test
    @DisplayName("Observable은 push 방식의 대표적인 구현체이다.")
    void push() throws InterruptedException {
        // given
        IntObservable observable = new IntObservable();
        Observer observer1 = new Observer() {
            List<LocalDateTime> saved = new ArrayList<>();

            @Override
            public void update(Observable obs, Object update) {
                saved.add(LocalDateTime.now());
                System.out.println("observer1: " + saved.stream()
                        .map(now -> now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                        .collect(Collectors.joining(", ")));
            }
        };
        Observer observer2 = new Observer() {
            List<LocalDateTime> saved = new ArrayList<>();

            @Override
            public void update(Observable obs, Object update) {
                saved.add(LocalDateTime.now());
                System.out.println("observer2: " + saved.stream()
                        .map(now -> now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                        .collect(Collectors.joining(", ")));
            }
        };
        observable.addObserver(observer1);
        IntObservable observable2 = new IntObservable();
        observable2.addObserver(observer1);

        Executor executor = Executors.newFixedThreadPool(1);

        // when & then
        executor.execute(observable);
        executor.execute(observable2);

        Thread.sleep(3000);
    }

    static class IntObservable extends Observable implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                setChanged();
                notifyObservers(i);
            }
        }
    }
}
