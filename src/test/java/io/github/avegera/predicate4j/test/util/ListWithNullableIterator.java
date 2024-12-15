package io.github.avegera.predicate4j.test.util;

import java.util.ArrayList;

public class ListWithNullableIterator<E> extends ArrayList<E> {

    @Override
    @SuppressWarnings("ConstantConditions")
    public java.util.Iterator<E> iterator() {
        return null;
    }

    @Override
    public String toString() {
        return "#ListWithNullableIterator";
    }
}