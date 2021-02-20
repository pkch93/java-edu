package edu.pkch.enumeration;

public enum OrdinalEnumeration {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int order;

    OrdinalEnumeration(int order) {
        this.order = order;
    }
}
