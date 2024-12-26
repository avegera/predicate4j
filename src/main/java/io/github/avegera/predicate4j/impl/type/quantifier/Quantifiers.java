package io.github.avegera.predicate4j.impl.type.quantifier;

import java.util.function.Predicate;

public class Quantifiers {

    public static <E> boolean atLeast(int times, Iterable<E> iterable, Predicate<E> predicate) {
        int count = 0;
        for (E element : iterable) {
            if (!predicate.test(element)) {
                continue;
            }
            count++;
            if (count >= times) {
                return true;
            }
        }
        return false;
    }

    public static <E> boolean atLeastOne(Iterable<E> iterable, Predicate<E> predicate) {
        for (E element : iterable) {
            if (predicate.test(element)) {
                return true;
            }
        }
        return false;
    }

    public static <E> boolean each(Iterable<E> iterable, Predicate<E> predicate) {
        for (E element : iterable) {
            if (!predicate.test(element)) {
                return false;
            }
        }
        return true;
    }

    public static <E> boolean exactly(int times, Iterable<E> iterable, Predicate<E> predicate) {
        int count = 0;
        for (E element : iterable) {
            if (!predicate.test(element)) {
                continue;
            }

            count++;
            if (count > times) {
                return false;
            }
        }
        return count == times;
    }

    public static <E> boolean exactlyOne(Iterable<E> iterable, Predicate<E> predicate) {
        short count = 0;
        for (E element : iterable) {
            if (!predicate.test(element)) {
                continue;
            }

            count++;
            if (count > 1) {
                return false;
            }
        }
        return count == 1;
    }

    public static <E> boolean none(Iterable<E> iterable, Predicate<E> predicate) {
        for (E element : iterable) {
            if (predicate.test(element)) {
                return false;
            }
        }
        return true;
    }
}