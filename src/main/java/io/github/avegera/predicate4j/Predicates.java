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

    //Object predicates

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

    //Boolean predicates

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

    //Collection predicates

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

    public static <T extends Collection<R>, R> Predicate<T> containsElement(R element) {
        return collection -> collection != null && collection.contains(element);
    }

    public static <T extends Collection<R>, R> Predicate<T> notContainsElement(R element) {
        return collection -> collection == null || !collection.contains(element);
    }

    //String predicates

    public static Predicate<String> isEmptyString() {
        return str -> str == null || str.isEmpty();
    }

    public static Predicate<String> notEmptyString() {
        return str -> str != null && !str.isEmpty();
    }

    public static Predicate<String> containsSubstring(String substring) {
        return str -> str != null && str.contains(substring);
    }

    public static Predicate<String> notContainsSubstring(String substring) {
        return str -> str == null || !str.contains(substring);
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
}