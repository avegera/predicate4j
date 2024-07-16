package io.github.avegera.predicate4j.api;

public interface WhereNumber<T, N extends Number> extends WhereObject<T, N> {

    RichPredicate<T> isGreaterThan(N value);

    RichPredicate<T> isGreaterThanOrEqualTo(N value);

    RichPredicate<T> isLessThan(N value);

    RichPredicate<T> isLessThanOrEqualTo(N value);

    RichPredicate<T> isBetween(N startInclusive, N endInclusive);

    RichPredicate<T> notBetween(N startInclusive, N endInclusive);

    RichPredicate<T> isEven();

    RichPredicate<T> isOdd();
}