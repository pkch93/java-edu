package edu.pkch.tostring;

public class A {
    private final B b;

    public A(B b) {
        this.b = b;
        b.relation(this);
    }

    @Override
    public String toString() {
        return "A{" +
                "b=" + b +
                '}';
    }
}
