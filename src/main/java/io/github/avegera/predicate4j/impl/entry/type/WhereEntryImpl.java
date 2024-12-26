package io.github.avegera.predicate4j.impl.entry.type;

import io.github.avegera.predicate4j.api.entry.common.WhereEntry;
import io.github.avegera.predicate4j.api.entry.quantifier.WhereEntryQuantifierType;
import io.github.avegera.predicate4j.api.type.collection.WhereCollection;
import io.github.avegera.predicate4j.api.type.collection.WhereIterable;
import io.github.avegera.predicate4j.api.type.collection.WhereList;
import io.github.avegera.predicate4j.api.type.collection.WhereSet;
import io.github.avegera.predicate4j.api.type.common.WhereBoolean;
import io.github.avegera.predicate4j.api.type.common.WhereNumber;
import io.github.avegera.predicate4j.api.type.common.WhereObject;
import io.github.avegera.predicate4j.api.type.common.WhereString;
import io.github.avegera.predicate4j.impl.entry.quantifier.*;
import io.github.avegera.predicate4j.impl.type.collection.WhereCollectionImpl;
import io.github.avegera.predicate4j.impl.type.collection.WhereIterableImpl;
import io.github.avegera.predicate4j.impl.type.collection.WhereListImpl;
import io.github.avegera.predicate4j.impl.type.collection.WhereSetImpl;
import io.github.avegera.predicate4j.impl.type.common.WhereBooleanImpl;
import io.github.avegera.predicate4j.impl.type.common.WhereNumberImpl;
import io.github.avegera.predicate4j.impl.type.common.WhereStringImpl;
import io.github.avegera.predicate4j.impl.type.quantifier.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class WhereEntryImpl implements WhereEntry {

    @Override
    public <T> WhereBoolean<T> booleanValue(Function<T, Boolean> mapper) {
        return new WhereBooleanImpl<>(mapper);
    }

    @Override
    public <T, E> WhereCollection<T, Collection<E>, E> collection(Function<T, Collection<E>> mapper) {
        return new WhereCollectionImpl<>(mapper);
    }

    @Override
    public <T, E> WhereIterable<T, Iterable<E>, E> iterable(Function<T, Iterable<E>> mapper) {
        return new WhereIterableImpl<>(mapper);
    }

    @Override
    public <T, E> WhereList<T, List<E>, E> list(Function<T, List<E>> mapper) {
        return new WhereListImpl<>(mapper);
    }

    @Override
    public <T, E> WhereSet<T, Set<E>, E> set(Function<T, Set<E>> mapper) {
        return new WhereSetImpl<>(mapper);
    }

    @Override
    public <T, N extends Number & Comparable<N>> WhereNumber<T, N> number(Function<T, N> mapper) {
        return new WhereNumberImpl<>(mapper);
    }

    @Override
    public <T> WhereString<T> string(Function<T, String> mapper) {
        return new WhereStringImpl<>(mapper);
    }

    @Override
    public WhereEntryQuantifierType atLeast(int times) {
        return new WhereEntryAtLeastTypeImpl(times);
    }

    @Override
    public <T, E> WhereObject<T, E> atLeast(int times, Function<T, Iterable<E>> mapper) {
        return new WhereAtLeastImpl<>(times, mapper);
    }

    @Override
    public WhereEntryQuantifierType atLeastOne() {
        return new WhereEntryAtLeastOneTypeImpl();
    }

    @Override
    public <T, E> WhereObject<T, E> atLeastOne(Function<T, Iterable<E>> mapper) {
        return new WhereAtLeastOneImpl<>(mapper);
    }

    @Override
    public WhereEntryQuantifierType exactly(int times) {
        return new WhereEntryExactlyTypeImpl(times);
    }

    @Override
    public <T, E> WhereObject<T, E> exactly(int times, Function<T, Iterable<E>> mapper) {
        return new WhereExactlyImpl<>(times, mapper);
    }

    @Override
    public WhereEntryQuantifierType exactlyOne() {
        return new WhereEntryExactlyOneTypeImpl();
    }

    @Override
    public <T, E> WhereObject<T, E> exactlyOne(Function<T, Iterable<E>> mapper) {
        return new WhereExactlyOneImpl<>(mapper);
    }

    @Override
    public WhereEntryQuantifierType each() {
        return new WhereEntryEachTypeImpl();
    }

    @Override
    public <T, E> WhereObject<T, E> each(Function<T, Iterable<E>> mapper) {
        return new WhereEachImpl<>(mapper);
    }

    @Override
    public WhereEntryQuantifierType none() {
        return new WhereEntryNoneTypeImpl();
    }

    @Override
    public <T, E> WhereObject<T, E> none(Function<T, Iterable<E>> mapper) {
        return new WhereNoneImpl<>(mapper);
    }
}