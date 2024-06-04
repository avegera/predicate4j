package io.github.avegera.predicate4j.api;

public interface WhereObject<T, R> {

    RichPredicate<T> isEqualTo(R value);

    RichPredicate<T> isInstanceOf(Class<?> clazz);

    RichPredicate<T> isNull();

    RichPredicate<T> notEqualTo(R value);

    RichPredicate<T> notInstanceOf(Class<?> clazz);

    RichPredicate<T> notNull();
}