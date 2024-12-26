package io.github.avegera.predicate4j.api.type.common;

import io.github.avegera.predicate4j.api.core.FluentPredicate;

public interface WhereBoolean<T> extends WhereObject<T, Boolean> {

    FluentPredicate<T> isTrue();

    FluentPredicate<T> notTrue();

    FluentPredicate<T> isFalse();

    FluentPredicate<T> notFalse();
}