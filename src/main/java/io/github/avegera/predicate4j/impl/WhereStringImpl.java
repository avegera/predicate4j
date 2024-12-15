package io.github.avegera.predicate4j.impl;

import io.github.avegera.predicate4j.Predicates;
import io.github.avegera.predicate4j.api.FluentPredicate;
import io.github.avegera.predicate4j.api.WhereNumber;
import io.github.avegera.predicate4j.api.WhereString;

import java.util.function.Function;

public class WhereStringImpl<T> extends WhereObjectImpl<T, String> implements WhereString<T> {

    public WhereStringImpl(Function<T, String> mapper) {
        super(mapper);
    }

    public WhereStringImpl(Function<T, String> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
    }

    @Override
    public FluentPredicate<T> isEmpty() {
        return getPredicate(Predicates.isEmptyString());
    }

    @Override
    public FluentPredicate<T> notEmpty() {
        return getPredicate(Predicates.notEmptyString());
    }

    @Override
    public FluentPredicate<T> contains(String substring) {
        return getPredicate(Predicates.containsSubstring(substring));
    }

    @Override
    public FluentPredicate<T> notContains(String substring) {
        return getPredicate(Predicates.notContainsSubstring(substring));
    }

    @Override
    public FluentPredicate<T> startsWith(String prefix) {
        return getPredicate(Predicates.startsWith(prefix));
    }

    @Override
    public FluentPredicate<T> notStartsWith(String prefix) {
        return getPredicate(Predicates.notStartsWith(prefix));
    }

    @Override
    public FluentPredicate<T> endsWith(String suffix) {
        return getPredicate(Predicates.endsWith(suffix));
    }

    @Override
    public FluentPredicate<T> notEndsWith(String suffix) {
        return getPredicate(Predicates.notEndsWith(suffix));
    }

    @Override
    public FluentPredicate<T> matches(String regex) {
        return getPredicate(Predicates.matches(regex));
    }

    @Override
    public FluentPredicate<T> notMatches(String regex) {
        return getPredicate(Predicates.notMatches(regex));
    }

    @Override
    public WhereNumber<T, Integer> length() {
        return new WhereNumberImpl<>(mapper == null ? null : this::getLength, previousPredicate);
    }

    private Integer getLength(T object) {
        return getInt(object, String::length);
    }
}