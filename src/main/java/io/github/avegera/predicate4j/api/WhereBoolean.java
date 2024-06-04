package io.github.avegera.predicate4j.api;

public interface WhereBoolean<T> extends WhereObject<T, Boolean> {

    RichPredicate<T> isTrue();

    RichPredicate<T> notTrue();

    RichPredicate<T> isFalse();

    RichPredicate<T> notFalse();
}