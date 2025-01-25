package io.github.avegera.predicate4j;

import java.util.function.Predicate;

public class Quantifiers {

    private Quantifiers() {
        //private constructor
    }

    @SuppressWarnings("ConstantConditions")
    public static <E> boolean atLeast(int times, Iterable<E> iterable, Predicate<E> predicate) {
        if (iterable == null || iterable.iterator() == null) {
            return false;
        }

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

    @SuppressWarnings("ConstantConditions")
    public static <E> boolean atLeastOne(Iterable<E> iterable, Predicate<E> predicate) {
        if (iterable == null || iterable.iterator() == null) {
            return false;
        }

        for (E element : iterable) {
            if (predicate.test(element)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("ConstantConditions")
    public static <E> boolean each(Iterable<E> iterable, Predicate<E> predicate) {
        if (iterable == null || iterable.iterator() == null) {
            return false;
        }

        for (E element : iterable) {
            if (!predicate.test(element)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("ConstantConditions")
    public static <E> boolean exactly(int times, Iterable<E> iterable, Predicate<E> predicate) {
        if (iterable == null || iterable.iterator() == null) {
            return false;
        }

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

    @SuppressWarnings("ConstantConditions")
    public static <E> boolean exactlyOne(Iterable<E> iterable, Predicate<E> predicate) {
        if (iterable == null || iterable.iterator() == null) {
            return false;
        }

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

    @SuppressWarnings("ConstantConditions")
    public static <E> boolean none(Iterable<E> iterable, Predicate<E> predicate) {
        if (iterable == null || iterable.iterator() == null) {
            return false;
        }

        for (E element : iterable) {
            if (predicate.test(element)) {
                return false;
            }
        }
        return true;
    }
}