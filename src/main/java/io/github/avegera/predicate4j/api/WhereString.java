package io.github.avegera.predicate4j.api;

public interface WhereString<T> extends WhereObject<T, String> {

    FluentPredicate<T> isEmpty();

    FluentPredicate<T> notEmpty();

    FluentPredicate<T> contains(String substring);

    FluentPredicate<T> notContains(String substring);

    FluentPredicate<T> startsWith(String prefix);

    FluentPredicate<T> notStartsWith(String prefix);

    FluentPredicate<T> endsWith(String suffix);

    FluentPredicate<T> notEndsWith(String suffix);

    FluentPredicate<T> matches(String regex);

    FluentPredicate<T> notMatches(String regex);

    WhereNumber<T, Integer> length();
}