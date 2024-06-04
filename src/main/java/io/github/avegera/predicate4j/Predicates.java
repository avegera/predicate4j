package io.github.avegera.predicate4j;

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
}