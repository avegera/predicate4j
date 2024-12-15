package io.github.avegera.predicate4j.impl;

import io.github.avegera.predicate4j.api.WhereBoolean;
import io.github.avegera.predicate4j.api.WhereNumber;
import io.github.avegera.predicate4j.api.WhereString;
import io.github.avegera.predicate4j.api.WhereType;
import io.github.avegera.predicate4j.api.collection.WhereList;
import io.github.avegera.predicate4j.impl.collection.WhereListImpl;

import java.util.List;
import java.util.function.Function;

public class WhereTypeImpl implements WhereType {

    @Override
    public <T> WhereBoolean<T> booleanValue(Function<T, Boolean> mapper) {
        return new WhereBooleanImpl<>(mapper);
    }

    @Override
    public <T, E> WhereList<T, List<E>, E> list(Function<T, List<E>> mapper) {
        return new WhereListImpl<>(mapper);
    }

    @Override
    public <T, N extends Number & Comparable<N>> WhereNumber<T, N> number(Function<T, N> mapper) {
        return new WhereNumberImpl<>(mapper);
    }

    @Override
    public <T> WhereString<T> string(Function<T, String> mapper) {
        return new WhereStringImpl<>(mapper);
    }
}