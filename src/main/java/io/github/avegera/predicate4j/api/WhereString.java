package io.github.avegera.predicate4j.api;

public interface WhereString<T> extends WhereObject<T, String> {

    RichPredicate<T> isEmpty();

    RichPredicate<T> notEmpty();

    RichPredicate<T> contains(String substring);

    RichPredicate<T> notContains(String substring);

    RichPredicate<T> startsWith(String prefix);

    RichPredicate<T> notStartsWith(String prefix);

    RichPredicate<T> endsWith(String suffix);

    RichPredicate<T> notEndsWith(String suffix);

    RichPredicate<T> matches(String regex);

    RichPredicate<T> notMatches(String regex);

    WhereNumber<T, Integer> length();
}