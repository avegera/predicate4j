package io.github.avegera.predicate4j.impl.conjuction.common;

import io.github.avegera.predicate4j.api.conjunction.common.WhereConjunction;
import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.api.type.collection.WhereCollection;
import io.github.avegera.predicate4j.api.type.collection.WhereIterable;
import io.github.avegera.predicate4j.api.type.collection.WhereList;
import io.github.avegera.predicate4j.api.type.collection.WhereSet;
import io.github.avegera.predicate4j.api.type.common.WhereBoolean;
import io.github.avegera.predicate4j.api.type.common.WhereNumber;
import io.github.avegera.predicate4j.api.type.common.WhereObject;
import io.github.avegera.predicate4j.api.type.common.WhereString;
import io.github.avegera.predicate4j.impl.type.collection.WhereCollectionImpl;
import io.github.avegera.predicate4j.impl.type.collection.WhereIterableImpl;
import io.github.avegera.predicate4j.impl.type.collection.WhereListImpl;
import io.github.avegera.predicate4j.impl.type.collection.WhereSetImpl;
import io.github.avegera.predicate4j.impl.type.common.WhereBooleanImpl;
import io.github.avegera.predicate4j.impl.type.common.WhereNumberImpl;
import io.github.avegera.predicate4j.impl.type.common.WhereStringImpl;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

public class WhereConjunctionImpl<T> implements WhereConjunction<T> {

    private final FluentPredicate<T> previousPredicate;

    public WhereConjunctionImpl(FluentPredicate<T> previousPredicate) {
        this.previousPredicate = previousPredicate;
    }

    @Override
    public WhereBoolean<T> booleanValue(Function<T, Boolean> mapper) {
        return getWhere(WhereBooleanImpl::new, mapper);
    }

    @Override
    public <E> WhereCollection<T, Collection<E>, E> collection(Function<T, Collection<E>> mapper) {
        return getWhere(WhereCollectionImpl::new, mapper);
    }

    @Override
    public <E> WhereIterable<T, Iterable<E>, E> iterable(Function<T, Iterable<E>> mapper) {
        return getWhere(WhereIterableImpl::new, mapper);
    }

    @Override
    public <E> WhereList<T, List<E>, E> list(Function<T, List<E>> mapper) {
        return getWhere(WhereListImpl::new, mapper);
    }

    @Override
    public <E> WhereSet<T, Set<E>, E> set(Function<T, Set<E>> mapper) {
        return getWhere(WhereSetImpl::new, mapper);
    }

    @Override
    public <N extends Number & Comparable<N>> WhereNumber<T, N> number(Function<T, N> mapper) {
        return getWhere(WhereNumberImpl::new, mapper);
    }

    @Override
    public WhereString<T> string(Function<T, String> mapper) {
        return getWhere(WhereStringImpl::new, mapper);
    }

    private <R, W extends WhereObject<T, R>> W getWhere(BiFunction<Function<T, R>, FluentPredicate<T>, W> constructor, Function<T, R> mapper) {
        return constructor.apply(mapper, previousPredicate);
    }
}