package io.github.avegera.predicate4j.api;

import java.util.function.Function;
import java.util.function.Predicate;

public interface RichPredicate<T> extends Predicate<T> {

    <R> WhereObject<T, R> and(Function<T, R> mapper);
}