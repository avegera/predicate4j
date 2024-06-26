package io.github.avegera.predicate4j;

import io.github.avegera.predicate4j.api.WhereObject;
import io.github.avegera.predicate4j.api.WhereType;
import io.github.avegera.predicate4j.impl.WhereObjectImpl;
import io.github.avegera.predicate4j.impl.WhereTypeImpl;

import java.util.function.Function;

public class Where {

    private Where() {
        //private constructor
    }

    public static <T, R> WhereObject<T, R> where(Function<T, R> mapper) {
        return new WhereObjectImpl<>(mapper);
    }

    public static WhereType where() {
        return new WhereTypeImpl();
    }
}