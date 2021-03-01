package edu.pkch.functionalinterface;

import java.util.function.Supplier;

public interface WithSupplier<T> extends Supplier<T> {
    T supplyWith(T t);
}
