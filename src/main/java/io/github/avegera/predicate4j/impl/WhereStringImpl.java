package io.github.avegera.predicate4j.impl;

import io.github.avegera.predicate4j.Predicates;
import io.github.avegera.predicate4j.api.RichPredicate;
import io.github.avegera.predicate4j.api.WhereString;

import java.util.function.Function;

public class WhereStringImpl<T> extends WhereObjectImpl<T, String> implements WhereString<T> {

    public WhereStringImpl(Function<T, String> mapper) {
        super(mapper);
    }

    @Override
    public RichPredicate<T> isEmpty() {
        return getPredicate(Predicates.isEmptyString());
    }

    @Override
    public RichPredicate<T> notEmpty() {
        return getPredicate(Predicates.notEmptyString());
    }

    @Override
    public RichPredicate<T> contains(String substring) {
        return getPredicate(Predicates.containsSubstring(substring));
    }

    @Override
    public RichPredicate<T> notContains(String substring) {
        return getPredicate(Predicates.notContainsSubstring(substring));
    }

    @Override
    public RichPredicate<T> startsWith(String prefix) {
        return getPredicate(Predicates.startsWith(prefix));
    }

    @Override
    public RichPredicate<T> notStartsWith(String prefix) {
        return getPredicate(Predicates.notStartsWith(prefix));
    }

    @Override
    public RichPredicate<T> endsWith(String suffix) {
        return getPredicate(Predicates.endsWith(suffix));
    }

    @Override
    public RichPredicate<T> notEndsWith(String suffix) {
        return getPredicate(Predicates.notEndsWith(suffix));
    }

    @Override
    public RichPredicate<T> matches(String regex) {
        return getPredicate(Predicates.matches(regex));
    }

    @Override
    public RichPredicate<T> notMatches(String regex) {
        return getPredicate(Predicates.notMatches(regex));
    }
}