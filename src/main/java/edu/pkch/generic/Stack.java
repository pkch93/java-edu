package edu.pkch.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Stack<E> {
    private final List<E> stack = new ArrayList<>();
    private int position = -1;

    public void push(E element) {
        position += 1;
        stack.add(element);
    }

    public void pushAll(Collection<? extends E> elements) {
        position += elements.size();
        stack.addAll(elements);
    }

    public E pop() {
        E popElement = stack.remove(position);
        position -= 1;
        return popElement;
    }

    public void popAll(Collection<? super E> dst) {
        dst.addAll(stack);
    }

    public boolean isEmpty() {
        return position == -1;
    }

    public int stackSize() {
        return position + 1;
    }
}
