package io.github.avegera.predicate4j;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * This class contains library of common java predicates.
 */
public class Predicates {

    private Predicates() {
        //private constructor
    }

    //region [Category: Object Predicates]

    public static <T> Predicate<T> isEqualTo(T object) {
        return it -> Objects.equals(it, object);
    }

    public static <T> Predicate<T> notEqualTo(T object) {
        return it -> !Objects.equals(it, object);
    }

    public static <T> Predicate<T> isNull() {
        return Objects::isNull;
    }

    public static <T> Predicate<T> notNull() {
        return Objects::nonNull;
    }

    public static <T> Predicate<T> isInstanceOf(Class<?> clazz) {
        return obj -> clazz != null && clazz.isInstance(obj);
    }

    public static <T> Predicate<T> notInstanceOf(Class<?> clazz) {
        return obj -> clazz == null || !clazz.isInstance(obj);
    }

    public static <T> Predicate<T> alwaysTrue() {
        return it -> true;
    }

    public static <T> Predicate<T> alwaysFalse() {
        return it -> false;
    }

    public static <T> Predicate<T> in(Collection<? extends T> collection) {
        return it -> collection != null && collection.contains(it);
    }

    public static <T> Predicate<T> notIn(Collection<? extends T> collection) {
        return it -> collection == null || !collection.contains(it);
    }

    //endregion [Category: Object Predicates]

    //region [Category: Boolean Predicates]

    public static Predicate<Boolean> isTrue() {
        return bool -> bool != null && bool;
    }

    public static Predicate<Boolean> notTrue() {
        return bool -> bool == null || !bool;
    }

    public static Predicate<Boolean> isFalse() {
        return bool -> bool != null && !bool;
    }

    public static Predicate<Boolean> notFalse() {
        return bool -> bool == null || bool;
    }

    //endregion [Category: Boolean Predicates]

    //region [Category: Collection Predicates]

    public static <T extends Collection<?>> Predicate<T> isEmpty() {
        return collection -> collection == null || collection.isEmpty();
    }

    public static <T extends Collection<?>> Predicate<T> notEmpty() {
        return collection -> collection != null && !collection.isEmpty();
    }

    public static <T extends Collection<?>> Predicate<T> hasSize(int size) {
        return collection -> (collection != null ? collection.size() : 0) == size;
    }

    public static <T extends Collection<?>> Predicate<T> notHaveSize(int size) {
        return collection -> (collection != null ? collection.size() : 0) != size;
    }

    public static <T extends Collection<E>, E> Predicate<T> containsElement(E element) {
        return collection -> collection != null && collection.contains(element);
    }

    public static <T extends Collection<E>, E> Predicate<T> notContainsElement(E element) {
        return collection -> collection == null || !collection.contains(element);
    }

    public static <T extends Collection<E>, E> Predicate<T> containsAllElements(Collection<E> elements) {
        return collection -> collection != null && elements != null && collection.containsAll(elements);
    }

    public static <T extends Collection<E>, E> Predicate<T> notContainsAllElements(Collection<E> elements) {
        return collection -> collection == null || elements == null || !collection.containsAll(elements);
    }

    //endregion [Category: Collection Predicates]

    //region [Category: Number Predicates]

    public static <N extends Number & Comparable<N>> Predicate<N> isGreaterThan(N value) {
        return number -> number != null && value != null && number.compareTo(value) > 0;
    }

    public static <N extends Number & Comparable<N>> Predicate<N> isLessThan(N value) {
        return number -> number != null && value != null && number.compareTo(value) < 0;
    }

    public static <N extends Number & Comparable<N>> Predicate<N> isGreaterThanOrEqualTo(N value) {
        return number -> number != null && value != null && number.compareTo(value) >= 0;
    }

    public static <N extends Number & Comparable<N>> Predicate<N> isLessThanOrEqualTo(N value) {
        return number -> number != null && value != null && number.compareTo(value) <= 0;
    }

    public static <N extends Number & Comparable<N>> Predicate<N> isBetween(N startInclusive, N endInclusive) {
        return number -> number != null && startInclusive != null && endInclusive != null && number.compareTo(startInclusive) >= 0 && number.compareTo(endInclusive) <= 0;
    }

    public static <N extends Number & Comparable<N>> Predicate<N> notBetween(N startInclusive, N endInclusive) {
        return number -> number == null || startInclusive == null || endInclusive == null || number.compareTo(startInclusive) < 0 || number.compareTo(endInclusive) > 0;
    }

    public static <N extends Number> Predicate<N> isEven() {
        return number -> number != null && number.longValue() % 2 == 0;
    }

    public static <N extends Number> Predicate<N> isOdd() {
        return number -> number != null && number.longValue() % 2 != 0;
    }

    //endregion [Category: Number Predicates]

    //region [Category: String Predicates]

    public static Predicate<String> isEmptyString() {
        return str -> str == null || str.isEmpty();
    }

    public static Predicate<String> notEmptyString() {
        return str -> str != null && !str.isEmpty();
    }

    public static Predicate<String> containsSubstring(String substring) {
        return str -> str != null && substring != null && str.contains(substring);
    }

    public static Predicate<String> notContainsSubstring(String substring) {
        return str -> str == null || substring == null || !str.contains(substring);
    }

    public static Predicate<String> startsWith(String prefix) {
        return str -> str != null && prefix != null && str.startsWith(prefix);
    }

    public static Predicate<String> notStartsWith(String prefix) {
        return str -> str == null || prefix == null || !str.startsWith(prefix);
    }

    public static Predicate<String> endsWith(String suffix) {
        return str -> str != null && suffix != null && str.endsWith(suffix);
    }

    public static Predicate<String> notEndsWith(String suffix) {
        return str -> str == null || suffix == null || !str.endsWith(suffix);
    }

    public static Predicate<String> matches(String regex) {
        return str -> str != null && regex != null && str.matches(regex);
    }

    public static Predicate<String> notMatches(String regex) {
        return str -> str == null || regex == null || !str.matches(regex);
    }

    //endregion [Category: String Predicates]
}