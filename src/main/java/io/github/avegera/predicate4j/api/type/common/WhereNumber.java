package io.github.avegera.predicate4j.api.type.common;

import io.github.avegera.predicate4j.api.core.FluentPredicate;

public interface WhereNumber<T, N extends Number> extends WhereObject<T, N> {

    FluentPredicate<T> isGreaterThan(N value);

    FluentPredicate<T> isGreaterThanOrEqualTo(N value);

    FluentPredicate<T> isLessThan(N value);

    FluentPredicate<T> isLessThanOrEqualTo(N value);

    FluentPredicate<T> isBetween(N startInclusive, N endInclusive);

    FluentPredicate<T> notBetween(N startInclusive, N endInclusive);

    FluentPredicate<T> isEven();

    FluentPredicate<T> isOdd();
}