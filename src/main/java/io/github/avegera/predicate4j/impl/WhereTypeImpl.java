package io.github.avegera.predicate4j.impl;

import io.github.avegera.predicate4j.api.WhereBoolean;
import io.github.avegera.predicate4j.api.WhereList;
import io.github.avegera.predicate4j.api.WhereString;
import io.github.avegera.predicate4j.api.WhereType;

import java.util.List;
import java.util.function.Function;

public class WhereTypeImpl implements WhereType {

    @Override
    public <T> WhereBoolean<T> booleanValue(Function<T, Boolean> mapper) {
        return new WhereBooleanImpl<>(mapper);
    }

    @Override
    public <T, R> WhereList<T, R> list(Function<T, List<R>> mapper) {
        return new WhereListImpl<>(mapper);
    }

    @Override
    public <T> WhereString<T> string(Function<T, String> mapper) {
        return new WhereStringImpl<>(mapper);
    }
}