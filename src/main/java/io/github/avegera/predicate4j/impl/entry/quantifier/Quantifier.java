package io.github.avegera.predicate4j.impl.entry.quantifier;

import java.util.function.Predicate;

public interface Quantifier {

    <E> boolean apply(Iterable<E> iterable, Predicate<E> predicate);
}