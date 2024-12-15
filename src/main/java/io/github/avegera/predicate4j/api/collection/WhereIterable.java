package io.github.avegera.predicate4j.api.collection;

import io.github.avegera.predicate4j.api.RichPredicate;
import io.github.avegera.predicate4j.api.WhereObject;

public interface WhereIterable<T, R extends Iterable<E>, E> extends WhereObject<T, R> {

    RichPredicate<T> isEmpty();

    RichPredicate<T> notEmpty();
}