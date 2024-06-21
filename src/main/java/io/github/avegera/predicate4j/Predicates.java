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

    public static <T extends Collection<R>, R> Predicate<T> contains(R element) {
        return collection -> collection != null && collection.contains(element);
    }

    public static <T extends Collection<R>, R> Predicate<T> notContain(R element) {
        return collection -> collection == null || !collection.contains(element);
    }
}