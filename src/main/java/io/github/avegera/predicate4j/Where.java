package io.github.avegera.predicate4j;

import io.github.avegera.predicate4j.api.entry.common.WhereEntry;
import io.github.avegera.predicate4j.api.type.common.WhereObject;
import io.github.avegera.predicate4j.impl.entry.type.WhereEntryImpl;
import io.github.avegera.predicate4j.impl.type.common.WhereObjectImpl;

import java.util.function.Function;

public class Where {

    private Where() {
        //private constructor
    }

    public static <T, R> WhereObject<T, R> where(Function<T, R> mapper) {
        return new WhereObjectImpl<>(mapper);
    }

    public static WhereEntry where() {
        return new WhereEntryImpl();
    }
}