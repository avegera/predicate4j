package io.github.avegera.predicate4j.impl.entry.quantifier.type;

import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.api.type.common.WhereNumber;
import io.github.avegera.predicate4j.impl.entry.quantifier.Quantifier;
import io.github.avegera.predicate4j.impl.type.common.WhereStringImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class WhereQuantifierStringImpl<T> extends WhereStringImpl<T, Iterable<String>> {

    private final Quantifier quantifier;

    public WhereQuantifierStringImpl(Quantifier quantifier, Function<T, Iterable<String>> mapper) {
        super(mapper);
        this.quantifier = quantifier;
    }

    public WhereQuantifierStringImpl(Quantifier quantifier, Function<T, Iterable<String>> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
        this.quantifier = quantifier;
    }

    @Override
    protected boolean test(Function<T, Iterable<String>> mapper, Predicate<String> predicate, T object) {
        return quantifier.apply(mapper.apply(object), predicate);
    }

    @Override
    public WhereNumber<T, Integer> length() {
        return new WhereQuantifierNumberImpl<>(quantifier, this::getLengths, previousPredicate);
    }

    @SuppressWarnings("ConstantConditions")
    private Iterable<Integer> getLengths(T object) {
        if (mapper == null) {
            return null;
        }

        Iterable<String> items = mapper.apply(object);
        if (items == null || items.iterator() == null) {
            return null;
        }

        List<Integer> lengths = new ArrayList<>();
        for (String item : items) {
            lengths.add(item == null ? 0 : item.length());
        }
        return lengths;
    }
}