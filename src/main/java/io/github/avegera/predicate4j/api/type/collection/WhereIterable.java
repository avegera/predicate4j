package io.github.avegera.predicate4j.api.type.collection;

import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.api.type.common.WhereObject;

public interface WhereIterable<T, R extends Iterable<E>, E> extends WhereObject<T, R> {

    FluentPredicate<T> isEmpty();

    FluentPredicate<T> notEmpty();
}