package edu.pkch.deadlock;

public interface SetObserver<E> {
    void added(ObservableSet<E> set, E element);
}
