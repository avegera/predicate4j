package io.github.avegera.predicate4j.api.collection;

import io.github.avegera.predicate4j.api.FluentPredicate;
import io.github.avegera.predicate4j.api.WhereObject;

public interface WhereIterable<T, R extends Iterable<E>, E> extends WhereObject<T, R> {

    FluentPredicate<T> isEmpty();

    FluentPredicate<T> notEmpty();
}