package io.github.avegera.predicate4j.impl;

import io.github.avegera.predicate4j.api.WhereBoolean;
import io.github.avegera.predicate4j.api.WhereType;

import java.util.function.Function;

public class WhereTypeImpl implements WhereType {

    @Override
    public <T> WhereBoolean<T> booleanValue(Function<T, Boolean> mapper) {
        return new WhereBooleanImpl<>(mapper);
    }
}