package io.github.avegera.predicate4j.test.util;

import java.util.HashSet;

public class SetWithNullableIterator<E> extends HashSet<E> {

    public static <E> SetWithNullableIterator<E> setWithNullableIterator() {
        return new SetWithNullableIterator<>();
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public java.util.Iterator<E> iterator() {
        return null;
    }

    @Override
    public String toString() {
        return "#SetWithNullableIterator";
    }
}