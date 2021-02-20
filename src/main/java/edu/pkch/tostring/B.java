package edu.pkch.tostring;

public class B {

    private A a;

    public void relation(A a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "B{" +
                "a=" + a +
                '}';
    }
}
