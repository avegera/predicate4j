package io.github.avegera.predicate4j.test.predicate;

import java.util.function.Function;

public class PredicateContext<T, R> {

    private final String name;
    private final String predicate;
    private final Function<T, R> mapper;
    private final T object;
    private final boolean expectedResult;

    private PredicateContext(Builder<T, R> builder) {
        this.name = builder.name;
        this.predicate = builder.predicate;
        this.mapper = builder.mapper;
        this.object = builder.object;
        this.expectedResult = builder.expectedResult;
    }

    public T object() {
        return object;
    }

    public String name() {
        return name;
    }

    public String predicate() {
        return predicate;
    }

    public Function<T, R> mapper() {
        return mapper;
    }

    public boolean expectedResult() {
        return expectedResult;
    }

    @Override
    public String toString() {
        return predicate;
    }

    public static <T, R> Builder<T, R> builder() {
        return new Builder<>();
    }

    public static class Builder<T, R> {

        private T object;

        private String name;

        private String predicate;

        private Function<T, R> mapper;

        private boolean expectedResult;

        public Builder<T, R> object(T object) {
            this.object = object;
            return this;
        }

        public Builder<T, R> predicate(String predicate) {
            this.predicate = predicate;
            return this;
        }

        public Builder<T, R> name(String name) {
            this.name = name;
            return this;
        }

        public Builder<T, R> mapper(Function<T, R> mapper) {
            this.mapper = mapper;
            return this;
        }

        public Builder<T, R> expectedResult(boolean expectedResult) {
            this.expectedResult = expectedResult;
            return this;
        }

        public PredicateContext<T, R> build() {
            return new PredicateContext<>(this);
        }
    }
}