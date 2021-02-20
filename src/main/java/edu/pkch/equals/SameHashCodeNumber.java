package edu.pkch.equals;

import java.util.Objects;

public class SameHashCodeNumber {
    private final int number;

    public SameHashCodeNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SameHashCodeNumber that = (SameHashCodeNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
