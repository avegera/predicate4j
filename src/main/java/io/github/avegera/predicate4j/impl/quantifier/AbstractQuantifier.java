package io.github.avegera.predicate4j.impl.quantifier;

import io.github.avegera.predicate4j.Predicates;
import io.github.avegera.predicate4j.api.FluentPredicate;
import io.github.avegera.predicate4j.api.WhereObject;
import io.github.avegera.predicate4j.impl.FluentPredicateImpl;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

import static io.github.avegera.predicate4j.Predicates.alwaysTrue;

//TODO: use composition instead of inheritance
public abstract class AbstractQuantifier<T, E> implements WhereObject<T, E> {

    private final Function<T, Iterable<E>> mapper;

    private final FluentPredicate<T> previousPredicate;

    public AbstractQuantifier(Function<T, Iterable<E>> mapper) {
        this.mapper = mapper;
        this.previousPredicate = null;
    }

    public AbstractQuantifier(Function<T, Iterable<E>> mapper, FluentPredicate<T> previousPredicate) {
        this.mapper = mapper;
        this.previousPredicate = previousPredicate;
    }

    @Override
    public FluentPredicate<T> isEqualTo(E value) {
        return getPredicate(Predicates.isEqualTo(value));
    }

    @Override
    public FluentPredicate<T> isInstanceOf(Class<?> clazz) {
        return getPredicate(Predicates.isInstanceOf(clazz));
    }

    @Override
    public FluentPredicate<T> isNull() {
        return getPredicate(Predicates.isNull());
    }

    @Override
    public FluentPredicate<T> notEqualTo(E value) {
        return getPredicate(Predicates.notEqualTo(value));
    }

    @Override
    public FluentPredicate<T> notInstanceOf(Class<?> clazz) {
        return getPredicate(Predicates.notInstanceOf(clazz));
    }

    @Override
    public FluentPredicate<T> notNull() {
        return getPredicate(Predicates.notNull());
    }

    @Override
    public FluentPredicate<T> in(Collection<E> collection) {
        return getPredicate(Predicates.in(collection));
    }

    @Override
    public FluentPredicate<T> notIn(Collection<E> collection) {
        return getPredicate(Predicates.notIn(collection));
    }

    @Override
    public FluentPredicate<T> accepts(Predicate<E> predicate) {
        return getPredicate(predicate);
    }

    @Override
    public FluentPredicate<T> rejects(Predicate<E> predicate) {
        return getPredicate(predicate == null ? alwaysTrue() : predicate.negate());
    }

    private FluentPredicate<T> getPredicate(Predicate<E> predicate) {
        Predicate<T> currentPredicate = getPredicateWithMapper(mapper, predicate);
        if (previousPredicate == null) {
            return new FluentPredicateImpl<>(currentPredicate);
        }
        return new FluentPredicateImpl<>(previousPredicate.and(currentPredicate));
    }

    @SuppressWarnings("ConstantConditions")
    private Predicate<T> getPredicateWithMapper(Function<T, Iterable<E>> mapper, Predicate<E> predicate) {
        return object -> {
            if (object == null || predicate == null || mapper == null) {
                return false;
            }

            Iterable<E> iterable = mapper.apply(object);
            if (iterable == null || iterable.iterator() == null || !iterable.iterator().hasNext()) {
                return false;
            }
            return test(iterable, predicate);
        };
    }

    abstract protected boolean test(Iterable<E> iterable, Predicate<E> predicate);
}
