package io.github.avegera.predicate4j.api;

import java.util.Collection;
import java.util.function.Predicate;

public interface WhereObject<T, R> {

    RichPredicate<T> isEqualTo(R value);

    RichPredicate<T> isInstanceOf(Class<?> clazz);

    RichPredicate<T> isNull();

    RichPredicate<T> notEqualTo(R value);

    RichPredicate<T> notInstanceOf(Class<?> clazz);

    RichPredicate<T> notNull();

    RichPredicate<T> in(Collection<R> collection);

    RichPredicate<T> notIn(Collection<R> collection);

    RichPredicate<T> accepts(Predicate<R> predicate);

    RichPredicate<T> rejects(Predicate<R> predicate);
}