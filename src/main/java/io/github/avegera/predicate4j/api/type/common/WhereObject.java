package io.github.avegera.predicate4j.api.type.common;

import io.github.avegera.predicate4j.api.core.FluentPredicate;

import java.util.Collection;
import java.util.function.Predicate;

public interface WhereObject<T, R> {

    FluentPredicate<T> isEqualTo(R value);

    FluentPredicate<T> isInstanceOf(Class<?> clazz);

    FluentPredicate<T> isNull();

    FluentPredicate<T> notEqualTo(R value);

    FluentPredicate<T> notInstanceOf(Class<?> clazz);

    FluentPredicate<T> notNull();

    FluentPredicate<T> in(Collection<R> collection);

    FluentPredicate<T> notIn(Collection<R> collection);

    FluentPredicate<T> accepts(Predicate<R> predicate);

    FluentPredicate<T> rejects(Predicate<R> predicate);
}